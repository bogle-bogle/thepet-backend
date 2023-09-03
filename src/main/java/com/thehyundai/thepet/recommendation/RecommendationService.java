package com.thehyundai.thepet.recommendation;

import java.util.List;

public interface RecommendationService {
    List<RecommendationVO> recommendProducts(Integer petId);
}
