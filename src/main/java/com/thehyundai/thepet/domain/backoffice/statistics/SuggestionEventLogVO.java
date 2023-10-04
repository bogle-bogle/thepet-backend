package com.thehyundai.thepet.domain.backoffice.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SuggestionEventLogVO {
    private List<StatisticsLogVO> suggestionLog;
    private List<StatisticsLogVO> mbtiLog;

}
