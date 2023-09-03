package com.thehyundai.thepet.recommendation;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class RecommendationServiceImpl implements RecommendationService {

    @Override
    public List<RecommendationVO> recommendProducts(Integer petId) {
        return null;
    }

}
