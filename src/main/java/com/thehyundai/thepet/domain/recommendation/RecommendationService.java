package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.product.ProductVO;

import java.util.List;

public interface RecommendationService {
    RecommendationVO recommendProductsSimply(Integer petId);
    RecommendationVO recommendProductsInDetail(Integer petId);

    List<ProductVO> recommendProductsByMbti(String mbtiType);
}
