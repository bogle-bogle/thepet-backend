package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.mypet.pet.PetMapper;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import com.thehyundai.thepet.domain.product.ProductMapper;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.global.EntityValidator;
import com.thehyundai.thepet.global.cmcode.CmCode;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@RequiredArgsConstructor
@Service
public class RecommendationServiceImpl implements RecommendationService {
    private final ProductMapper productMapper;
    private final PetMapper petMapper;
    private final EntityValidator entityValidator;

    @Override
    public List<RecommendationVO> recommendProducts(Integer petId) {
        PetVO petVO = entityValidator.getPresentPet(petId);
        recommendByPetInfo(petVO);
        return null;
    }

    public RecommendationVO recommendByPetInfo(PetVO petInfo) {
        // 1. 오늘 날짜 기준으로 강아지 나이 계산
        Integer petAge = calculatePetAge(petInfo.getBirth());
        String ageCmCode = CmCode.convertToPetAgeCode(petAge, petInfo.getSizeCode());
        petInfo.setAgeCode(ageCmCode);

        // 2. 강아지 정보를 기준으로 추천 상품 조회
        // 2-1. Product 정보 조회
        List<ProductVO> simpleRecommendations = productMapper.findProductsBySimplePetInfo(petInfo);      // 연령 + 최애 단백질 기준 추천
        // 2-2. Product + Order 정보 조회 (Redis 이용)
        List<ProductVO> advancedRecommendations = productMapper.findProductsByPetInfoAndOrderLog(petInfo);         // 반려동물의 MBTI 기준 추천


        // 3. 중복 제거 및 알러지 성분이 아닌 상품들로만 필터링
        List<String> allergies = petInfo.getAllergies();
        List<ProductVO> recommendations = Stream.concat(
                                                    productMapper.findProductsBySimplePetInfo(petInfo).stream(),
                                                    productMapper.findProductsByPetInfoAndOrderLog(petInfo).stream())
                                                .distinct()
                                                .filter(product -> !allergies.contains(product.getProteinCode()))
                                                .collect(Collectors.toList());

        return new RecommendationVO(petInfo, recommendations);
    }

    private Integer calculatePetAge(LocalDate birthDate) {
        LocalDate today = LocalDate.now();
        if ((birthDate != null) && (today != null)) {
            return Period.between(birthDate, today).getYears();
        } else {
            throw new BusinessException(ErrorCode.INVALID_BIRTHDATE);
        }
    }
}
