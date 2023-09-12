package com.thehyundai.thepet.domain.review;

import java.util.List;

public interface ReviewService {
    public List<ReviewVO> getAllReviews(String productId);
    public ReviewVO getReviewDetail(String id);
}
