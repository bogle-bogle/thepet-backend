package com.thehyundai.thepet.domain.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartVO {

    private String id;
    private int cnt;
    private String memberId;
    private String productId;

}
