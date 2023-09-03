package com.thehyundai.thepet.domain.recommendation;

import java.util.List;

public interface RecommendationService {
    List<RecommendationVO> recommendProducts(Integer petId);
}
