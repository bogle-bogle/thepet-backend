package com.thehyundai.thepet.domain.review;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReviewMapper {
    List<ReviewVO> selectAllReviews(int productId);
    ReviewVO selectReviewDetail(int id);
}
