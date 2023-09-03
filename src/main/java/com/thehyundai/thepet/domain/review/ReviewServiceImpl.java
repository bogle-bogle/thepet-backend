package com.thehyundai.thepet.domain.review;

import com.thehyundai.thepet.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.thehyundai.thepet.global.exception.ErrorCode.REVIEW_NOT_FOUND;

@Log4j2
@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewVO> getAllReviews(int productId) {
        return reviewMapper.selectAllReviews(productId);
    }

    @Override
    public ReviewVO getReviewDetail(int id) {
        ReviewVO result = reviewMapper.selectReviewDetail(id);
        if (result == null) {
            throw new BusinessException(REVIEW_NOT_FOUND);
        }
        return reviewMapper.selectReviewDetail(id);
    }
}
