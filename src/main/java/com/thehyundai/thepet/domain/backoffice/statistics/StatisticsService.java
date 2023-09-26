package com.thehyundai.thepet.domain.backoffice.statistics;

import java.util.List;

public interface StatisticsService {
    MainEventLogVO getMainEventLog();
    SuggestionEventLogVO getSuggestionEventLog();
    ProductRateVO getProductRateEventLog();
}
