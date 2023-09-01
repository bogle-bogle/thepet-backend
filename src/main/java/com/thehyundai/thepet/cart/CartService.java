package com.thehyundai.thepet.cart;

import java.util.List;

public interface CartService {

    CartVO insertCart(CartVO cartVO);

    CartVO updateCart(CartVO cartVO);

    int deleteCart(int id);

    List<CartVO> getCart(int memberId);

}
