package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.mypet.pet.PetMapper;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import com.thehyundai.thepet.domain.product.ProductMapper;
import com.thehyundai.thepet.domain.product.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final ProductMapper productMapper;
    private final PetMapper petMapper;

    @Override
    public List<RecommendationVO> recommendProducts(Integer petId) {
        PetVO petVO = petMapper.findPetWithAllergiesById(petId);
        recommendByPetInfo(petVO);
        return null;
    }

    public RecommendationVO recommendByPetInfo(PetVO petInfo) {
        List<ProductVO> products = productMapper.findProductsByPetInfo(petInfo);
        RecommendationVO result = new RecommendationVO(petInfo, products);
        return result;
    }

}
