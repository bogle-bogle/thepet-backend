package com.thehyundai.thepet.domain.recommendation;

public interface RecommendationService {
    RecommendationVO recommendProductsSimply(Integer petId);
    RecommendationVO recommendProductsInDetail(Integer petId);
}
