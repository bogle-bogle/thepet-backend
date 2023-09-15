package com.thehyundai.thepet.domain.review;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewVO {
    private String id;
    private String title;
    private String content;
    private String imgUrl;
    private int starRating;
    private String memberNickname;
    private String memberId;
    private String productId;
}
