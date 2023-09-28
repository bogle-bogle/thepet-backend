package com.thehyundai.thepet.external.tosspayment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
// 결제 승인 요청 DTO
public class PaymentReqDTO {

    // 일반결제
    private Integer amount;
    private String orderId;
    private String paymentKey;

    // 정기결제
    private String customerKey;
    private String orderName;

}
