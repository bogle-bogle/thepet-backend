package com.thehyundai.thepet.tosspayment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {

    private Integer amount;
    private String orderName;
    private String orderId;
    private String customerEmail;
    private String customerName;

    private String thePetSuccessUrl;
    private String thePetFailUrl;

}
