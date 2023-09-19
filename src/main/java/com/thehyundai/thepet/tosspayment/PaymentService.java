package com.thehyundai.thepet.tosspayment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.minidev.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Collections;

@Log4j2
@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TossPaymentConfig tossPaymentConfig;

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
}
