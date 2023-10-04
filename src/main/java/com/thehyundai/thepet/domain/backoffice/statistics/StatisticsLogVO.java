package com.thehyundai.thepet.domain.backoffice.statistics;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatisticsLogVO {
    private String element;
    private Integer count;
}
