package com.thehyundai.thepet.global;

import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.domain.subscription.CurationVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.domain.member.MemberMapper;
import com.thehyundai.thepet.domain.product.ProductMapper;
import com.thehyundai.thepet.domain.subscription.CurationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.thehyundai.thepet.global.exception.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class EntityValidator {
    private final MemberMapper memberMapper;
    private final ProductMapper productMapper;
    private final CurationMapper curationMapper;

    public MemberVO checkPresentMember(Integer memberId) {
        return memberMapper.findMemberById(memberId)
                           .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }

    public ProductVO checkPresentProduct(Integer productId) {
        return productMapper.findProductById(productId)
                            .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND));
    }

    public CurationVO checkPresentCuration(Integer curationId) {
        return curationMapper.findCurationById(curationId)
                     .orElseThrow(() -> new BusinessException(CURATION_NOT_FOUND));
    }

}
