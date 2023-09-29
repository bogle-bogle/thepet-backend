package com.thehyundai.thepet.external.tosspayment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardResDTO {
    private String mId;
    private String customerKey;
    private String authenticatedAt;
    private String method;
    private String billingKey;
    private String cardCompany;
    private String cardNumber;
    private Card card;
}

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
class Card {
    private String issuerCode;
    private String acquirerCode;
    private String number;
    private String cardType;
    private String ownerType;
}
