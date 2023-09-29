package com.thehyundai.thepet.domain.backoffice.statistics;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class StatisticsServiceImpl implements StatisticsService {

    private final StatisticsMapper statisticsMapper;

    @Override
    public MainEventLogVO getMainEventLog() {
        MainEventLogVO res = new MainEventLogVO();
        res.setMainLog(statisticsMapper.selectMainLog());
        res.setBannerLog(statisticsMapper.selectBannerLog());
        res.setRecommendLog(statisticsMapper.selectRecommendLog());
        return res;
    }

    @Override
    public SuggestionEventLogVO getSuggestionEventLog() {
        SuggestionEventLogVO res = new SuggestionEventLogVO();
        res.setSuggestionLog(statisticsMapper.selectSuggestion());
        res.setMbtiLog(statisticsMapper.selectMbti());

        return res;
    }

    @Override
    public ProductRateVO getProductRateEventLog() {
        ProductRateVO res = new ProductRateVO();
        res.setYRateList(statisticsMapper.selectProductCartYRate());
        res.setNRateList(statisticsMapper.selectProductCartNRate());
        return res;
    }
}
