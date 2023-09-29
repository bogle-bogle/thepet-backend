package com.thehyundai.thepet.domain.backoffice.statistics;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StatisticsMapper {
    List<StatisticsLogVO> selectMainLog();
    List<StatisticsLogVO> selectBannerLog();
    List<StatisticsLogVO> selectRecommendLog();

    List<StatisticsLogVO> selectSuggestion();
    List<StatisticsLogVO> selectMbti();

    List<ProductCartRateVO> selectProductCartYRate();
    List<ProductCartRateVO> selectProductCartNRate();
}
