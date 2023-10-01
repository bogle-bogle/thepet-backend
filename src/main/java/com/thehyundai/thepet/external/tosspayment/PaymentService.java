package com.thehyundai.thepet.external.tosspayment;

import com.google.gson.Gson;
import com.thehyundai.thepet.domain.member.MemberMapper;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TossPaymentConfig tossPaymentConfig;
    private final MemberMapper memberMapper;
    private final WebClient webClient;

    @Autowired
    public PaymentService(TossPaymentConfig tossPaymentConfig,
                          MemberMapper memberMapper,
                          WebClient.Builder webClientBuilder,
                          @Value("${toss.api.url}") String tossUrl) {
        String encodedAuth = Base64Utils.encodeToString(
                tossPaymentConfig.getTestSecretKey().getBytes());
        this.tossPaymentConfig = tossPaymentConfig;
        this.memberMapper = memberMapper;
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

}
