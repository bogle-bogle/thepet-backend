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
        
        // 2. 강아지 기준으로 추천 상품 조회
        List<ProductVO> products = productMapper.findProductsByPetInfo(petInfo);
        RecommendationVO result = new RecommendationVO(petInfo, products);
        return result;
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
