package com.thehyundai.thepet.cart;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CartVO {

    private int id;
    private int cnt;
    private int memberId;
    private int productId;

}
