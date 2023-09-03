package com.thehyundai.thepet.global;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.domain.member.MemberMapper;
import com.thehyundai.thepet.domain.product.ProductMapper;
import com.thehyundai.thepet.domain.subscription.CurationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.thehyundai.thepet.global.exception.ErrorCode.*;

@Component
@RequiredArgsConstructor
public class DataValidator {
    private final MemberMapper memberMapper;
    private final ProductMapper productMapper;
    private final CurationMapper curationMapper;


    public void checkPresentMember(Integer memberId) {
        memberMapper.findMemberById(memberId)
                    .orElseThrow(() -> new BusinessException(MEMBER_NOT_FOUND));
    }

    public void checkPresentProduct(Integer productId) {
        productMapper.findProductById(productId)
                     .orElseThrow(() -> new BusinessException(PRODUCT_NOT_FOUND));
    }

    public void checkPresentCuration(Integer productId) {
        curationMapper.findCurationById(productId)
                     .orElseThrow(() -> new BusinessException(CURATION_NOT_FOUND));
    }

}
