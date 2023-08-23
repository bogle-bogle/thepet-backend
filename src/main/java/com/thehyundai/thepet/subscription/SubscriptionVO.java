package com.thehyundai.thepet.subscription;

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
    private Integer id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer memberId;
    private Integer curationId;
    private Integer productId;
    private String CurationYn;
}
