package com.thehyundai.thepet.domain.backoffice.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MainEventLogVO {
    private List<StatisticsLogVO> mainLog;
    private List<StatisticsLogVO> bannerLog;
    private List<StatisticsLogVO> recommendLog;
}
