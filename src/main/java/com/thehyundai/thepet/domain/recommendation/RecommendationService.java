package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.product.ProductVO;

import java.util.List;

public interface RecommendationService {
    RecommendationVO recommendProductsSimply(String petId);
    RecommendationVO recommendProductsInDetail(String petId);
    List<ProductVO> recommendProductsByMbti(String mbtiType);
}
