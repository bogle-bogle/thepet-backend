package com.thehyundai.thepet.domain.subscription;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionVO {
    private String id;
    private LocalDate startDate;
    private LocalDate endDate;
    private String memberId;
    private String productId;
    private String curationYn;
}
