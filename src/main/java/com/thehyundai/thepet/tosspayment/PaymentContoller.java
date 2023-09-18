package com.thehyundai.thepet.tosspayment;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/payments/")
public class PaymentContoller {
    private final TossPaymentConfig tossPaymentConfig;

    @PostMapping("/toss")
    public ResponseEntity<?> requestTossPayment( @RequestBody PaymentDTO paymentDTO){
        log.info("결제 " + paymentDTO);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/toss/success")
    public ResponseEntity<?> tossPaymentSuccess(@PathVariable String paymentType,
                                                @PathVariable String paymentKey,
                                                @PathVariable String orderId,
                                                @PathVariable Integer amount){
        log.info("성공 ");
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
