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
public class CurationVO {
    private Integer id;
    private LocalDate paymentDate;
    private Integer product1Id;
    private Integer product2Id;
    private Integer product3Id;
}
