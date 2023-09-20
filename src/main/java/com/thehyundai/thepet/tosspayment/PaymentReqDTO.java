package com.thehyundai.thepet.tosspayment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
// 결제 승인 요청 DTO
public class PaymentReqDTO {

    private Integer amount;
    private String orderId;
    private String paymentKey;

}
