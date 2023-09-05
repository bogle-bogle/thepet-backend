package com.thehyundai.thepet.domain.review;

import java.util.List;

public interface ReviewService {
    public List<ReviewVO> getAllReviews(int productId);
    public ReviewVO getReviewDetail(int id);
}
