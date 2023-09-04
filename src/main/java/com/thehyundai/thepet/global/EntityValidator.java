package com.thehyundai.thepet.global;

import com.thehyundai.thepet.domain.member.MemberMapper;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.domain.mypet.pet.PetMapper;
import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import com.thehyundai.thepet.domain.product.ProductMapper;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.domain.subscription.CurationMapper;
import com.thehyundai.thepet.domain.subscription.CurationVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

import static com.thehyundai.thepet.global.exception.ErrorCode.*;

@Log4j2
@Component
@RequiredArgsConstructor
public class EntityValidator {
    private final MemberMapper memberMapper;
    private final ProductMapper productMapper;
    private final CurationMapper curationMapper;
    private final PetMapper petMapper;

    public MemberVO getPresentMember(Integer memberId) {
        return memberMapper.findMemberById(memberId)
                           .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }

    public ProductVO getPresentProduct(Integer productId) {
        return productMapper.findProductById(productId)
                            .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND));
    }

    public CurationVO getPresentCuration(Integer curationId) {
        return curationMapper.findCurationById(curationId)
                     .orElseThrow(() -> new BusinessException(CURATION_NOT_FOUND));
    }

    public PetVO getPresentPet(Integer id) {
        return petMapper.findPetWithAllergiesById(id)
                        .orElseThrow(() -> new BusinessException(PET_NOT_FOUND));
    }

}
