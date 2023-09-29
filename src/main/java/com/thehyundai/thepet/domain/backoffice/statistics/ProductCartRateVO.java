package com.thehyundai.thepet.domain.backoffice.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCartRateVO {
    private String element;
    private Integer isClickedCount;
    private Integer total;
    private Float rate;
}
