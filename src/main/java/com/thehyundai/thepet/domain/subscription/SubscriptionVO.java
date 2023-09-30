package com.thehyundai.thepet.domain.subscription;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SubscriptionVO {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String memberId;
    private String productId;
    private String curationYn;
}
