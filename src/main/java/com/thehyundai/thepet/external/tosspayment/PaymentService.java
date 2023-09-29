package com.thehyundai.thepet.external.tosspayment;

import com.google.gson.Gson;
import com.thehyundai.thepet.domain.member.MemberMapper;
import com.thehyundai.thepet.domain.member.MemberService;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.util.*;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TossPaymentConfig tossPaymentConfig;
    private final MemberMapper memberMapper;
    private final MemberService memberService;

    // 결제 승인 요청
    public String requestAcceptPayment(String paymentKey,
                                       String orderId,
                                       Integer amount) {

        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String encodedAuth = new String(Base64.getEncoder().encode(
                tossPaymentConfig.getTestSecretKey().getBytes(StandardCharsets.UTF_8)));

        headers.setBasicAuth(encodedAuth);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        JSONObject params = new JSONObject();
        params.put("paymentKey", paymentKey);
        params.put("orderId", orderId);
        params.put("amount", amount.toString());

        HttpEntity formEntity = new HttpEntity<>(params, headers);

        ResponseEntity<String> response = rest.postForEntity(
                tossPaymentConfig.getSuccessUrl(),
                formEntity,
                String.class
        );

        return response.getBody();
    }

    // 빌링키 발급
    public String issueBillingKey(String customerKey,
                                  String authKey) {

        String encodedAuth = new String(Base64.getEncoder().encode(
                tossPaymentConfig.getTestSecretKey().getBytes(StandardCharsets.UTF_8)));
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("customerKey", customerKey);
        bodyMap.put("authKey", authKey);

        WebClient webClient = WebClient.builder()
                .baseUrl(tossPaymentConfig.getBillingKeyUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Basic " + encodedAuth)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .build();

        String response = webClient.post()
                .bodyValue(bodyMap)
                .retrieve()
                .bodyToMono(String.class)
                .block();

        log.info(response);

        Gson gson = new Gson();
        CardResDTO cardResDTO = gson.fromJson(response, CardResDTO.class);
        String billingKey = cardResDTO.getBillingKey();
        String cardCompany = cardResDTO.getCardCompany();
        String cardNumber = cardResDTO.getCardNumber();
        String cardType = cardResDTO.getCard().getCardType();

        MemberVO memberVO = new MemberVO();
        memberVO.setId(customerKey);
        memberVO.setBillingKey(billingKey);
        memberVO.setCardCompany(cardCompany);
        memberVO.setCardNumber(cardNumber);
        memberVO.setCardType(cardType);

        // member의 빌링키 업데이트
        if (memberMapper.updateMemberBillingKey(memberVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        return response;
    }

}
