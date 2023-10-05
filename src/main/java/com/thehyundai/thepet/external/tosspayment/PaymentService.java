package com.thehyundai.thepet.external.tosspayment;

import com.google.gson.Gson;
import com.thehyundai.thepet.domain.member.MemberMapper;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.domain.order.OrderDetailMapper;
import com.thehyundai.thepet.domain.order.OrderDetailVO;
import com.thehyundai.thepet.domain.order.OrderMapper;
import com.thehyundai.thepet.domain.order.OrderVO;
import com.thehyundai.thepet.domain.subscription.SubsMapper;
import com.thehyundai.thepet.domain.subscription.SubscriptionVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TossPaymentConfig tossPaymentConfig;
    private final MemberMapper memberMapper;
    private final SubsMapper subsMapper;
    private final WebClient webClient;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public PaymentService(TossPaymentConfig tossPaymentConfig,
                          MemberMapper memberMapper,
                          SubsMapper subsMapper,
                          OrderMapper orderMapper,
                          OrderDetailMapper orderDetailMapper,
                          WebClient.Builder webClientBuilder,
                          @Value("${toss.api.url}") String tossUrl) {
        String encodedAuth = Base64Utils.encodeToString(
                tossPaymentConfig.getTestSecretKey().getBytes());
        this.tossPaymentConfig = tossPaymentConfig;
        this.memberMapper = memberMapper;
        this.subsMapper = subsMapper;
        this.orderMapper = orderMapper;
        this.orderDetailMapper = orderDetailMapper;
        this.webClient = webClientBuilder.baseUrl(tossUrl)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();
    }

    // 결제 승인 요청
    public String requestAcceptPayment(PaymentReqDTO paymentReqDTO) {

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("paymentKey", paymentReqDTO.getPaymentKey());
        bodyMap.put("orderId", paymentReqDTO.getOrderId());
        bodyMap.put("amount", String.valueOf(paymentReqDTO.getAmount()));

        String response = webClient.post()
                .uri("/payments/confirm")
                .bodyValue(bodyMap)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        return response;
    }

    // 빌링키 발급
    public String issueBillingKey(CardRegiDTO cardRegiDTO) {

        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("customerKey", cardRegiDTO.getCustomerKey());
        bodyMap.put("authKey", cardRegiDTO.getAuthKey());

        String response = webClient.post()
                .uri("/billing/authorizations/issue")
                .bodyValue(bodyMap)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        Gson gson = new Gson();
        CardResDTO cardResDTO = gson.fromJson(response, CardResDTO.class);

        MemberVO memberVO = MemberVO.builder()
                .id(cardRegiDTO.getCustomerKey())
                .billingKey(cardResDTO.getBillingKey())
                .cardCompany(cardResDTO.getCardCompany())
                .cardNumber(cardResDTO.getCardNumber())
                .cardType(cardResDTO.getCard().getCardType())
                .build();

        // member의 빌링키 업데이트
        if (memberMapper.updateMemberBillingKey(memberVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        return response;
    }

    // 정기결제 /v1/billing/{billingKey}
    @Scheduled(cron = "0 0 1 1 * ?")  // 매월 1일 1시에 실행
    public void monthlyRegularSubPayment() {
        List<SubscriptionVO> subscriptions = subsMapper.findAllProductSubscription();

        // Flux로 변환
        Flux<SubscriptionVO> subscriptionFlux = Flux.fromIterable(subscriptions);

        // 각 SubscriptionVO에 대해 비동기 API 호출을 수행
        Flux<ResultType> resultFlux = subscriptionFlux.flatMap(subscription -> {
            String billingKey = subscription.getBillingKey();
            return webClient.get()
                    .uri("/v1/billing/" + billingKey)
                    .retrieve()
                    .bodyToMono(ResultType.class);
        });

        resultFlux.collectList().subscribe(results -> {
            for (ResultType result : results) {
                // 결과를 토대로 Order와 OrderDetail 객체를 만들고 DB에 저장
                OrderVO order = buildOrder(result);
                if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

                List<OrderDetailVO> orderDetails = buildOrderDetails(result, order.getId());
                for (OrderDetailVO orderDetail : orderDetails) {
                    if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
                }
            }
        });
    }

    private List<OrderDetailVO> buildOrderDetails(ResultType result, String id) {

        return null;
    }

    private OrderVO buildOrder(ResultType result) {
        return null;
    }

}
