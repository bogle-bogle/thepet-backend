package com.thehyundai.thepet.external.tosspayment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/toss")
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/success")
    public ResponseEntity<String> tossPaymentSuccess(@RequestBody PaymentReqDTO paymentReqDTO){
        String result = paymentService.requestAcceptPayment(paymentReqDTO);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cardregister")
    public ResponseEntity<String> tossCardRegister(@RequestBody CardRegiDTO cardRegiDTO){
        String result = paymentService.issueBillingKey(cardRegiDTO);
        return ResponseEntity.ok(result);
    }
}
