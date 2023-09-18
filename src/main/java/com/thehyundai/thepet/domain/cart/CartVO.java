package com.thehyundai.thepet.domain.cart;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartVO {

    private String id;
    private int cnt;
    private String memberId;
    private String productId;
    private String createdAt;
    private String deleteYN;

    // 장바구니의 상품 정보
    private String name;
    private Integer price;
    private String mainImgUrl;

}
