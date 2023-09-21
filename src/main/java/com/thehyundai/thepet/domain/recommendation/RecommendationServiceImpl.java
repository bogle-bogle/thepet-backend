package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import com.thehyundai.thepet.domain.product.ProductMapper;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.global.cmcode.CmCode;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import com.thehyundai.thepet.global.util.EntityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Log4j2
@RequiredArgsConstructor
@Service
//@TimeTraceService
public class RecommendationServiceImpl implements RecommendationService {
    private final ProductMapper productMapper;
    private final EntityValidator entityValidator;

    @Override
    @Cacheable(value= "recommendProductSimply", key="#petId", cacheManager = "contentCacheManager")
    public RecommendationVO recommendProductsSimply(String petId) {
        // 0. 반려동물 정보 가져오기
        PetVO petInfo = entityValidator.getPresentPet(petId);

        // 1. 오늘 날짜 기준으로 반려동물 연령대 알아내기 -> 퍼피 / 어덜트 / 시니어
        Integer petAge = calculatePetAge(petInfo.getBirth());
        String ageCmCode = CmCode.convertToPetAgeCode(petAge, petInfo.getSizeCode()).getCodeValue();
        petInfo.setAgeCode(ageCmCode);

        // 2. 기본 정보를 기준으로 추천 상품 조회 (연령대 기준, 최애 단백질원, 알러지 배제)
        List<String> allergies = petInfo.getAllergies();

        String favoriteProtein = petInfo.getFavoriteProteinCode();
        List<ProductVO> searchedResult = (favoriteProtein != null) ? productMapper.findProductsByFavoriteProteinCode(favoriteProtein) : productMapper.findProductsByAgeCode(ageCmCode);

        List<ProductVO> simpleRecommendations = searchedResult.stream()
                                                              .filter(product -> !allergies.contains(product.getProteinCode()))
                                                              .limit(4)
                                                              .toList();
        return new RecommendationVO(petInfo, simpleRecommendations);
    }

    @Override
    @Cacheable(value= "recommendProductDetail", key="#petId", cacheManager = "contentCacheManager")
    public RecommendationVO recommendProductsInDetail(String petId) {
        // 0. 반려동물 정보 가져오기
        PetVO petInfo = entityValidator.getPresentPet(petId);

        // 1. 나의 반려동물과 같은 종, 같은 연령대 (1년차 내로)들이 많이 구매한 상품 조회
        List<String> allergies = petInfo.getAllergies();
        List<ProductVO> advancedRecommendations = productMapper.findProductsByPetInfoAndOrderLog(petInfo)
                                                               .stream()
                                                               .distinct()
                                                               .filter(product -> !allergies.contains(product.getProteinCode()))
                                                               .limit(4)
                                                               .toList();
        return new RecommendationVO(petInfo, advancedRecommendations);
    }

    @Override
    public List<ProductVO> recommendProductsByMbti(String mbtiType) {
        List<ProductVO> recommendations = productMapper.findProductsByMbti(mbtiType.toUpperCase())
                                                       .stream()
                                                       .distinct()
                                                       .limit(4)
                                                       .toList();
        return recommendations;
    }

    @Override
    public RecommendationVO recommendToyProductsByMbti(String mbtiType) {
        List<ProductVO> recommendations = productMapper.findToyProductsByMbti(mbtiType.toUpperCase())
                                                       .stream()
                                                       .distinct()
                                                       .limit(4)
                                                       .toList();
        return new RecommendationVO(null, recommendations);
    }

    @Override
    public RecommendationVO recommendSuppliesByMbti(String mbtiType) {
        List<ProductVO> recommendations = productMapper.findSuppliesByMbti(mbtiType.toUpperCase())
                                                       .stream()
                                                       .distinct()
                                                       .limit(4)
                                                       .toList();
        return new RecommendationVO(null, recommendations);
    }

    private Integer calculatePetAge(LocalDate birthDate) {
        if (birthDate != null) {
            return Period.between(birthDate, LocalDate.now()).getYears();
        }
        throw new BusinessException(ErrorCode.INVALID_BIRTHDATE);
    }

}
