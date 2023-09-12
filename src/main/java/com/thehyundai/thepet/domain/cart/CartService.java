package com.thehyundai.thepet.domain.cart;

import java.util.List;

public interface CartService {

    CartVO insertCart(CartVO cartVO);

    CartVO updateCart(CartVO cartVO);

    String deleteCart(String id);

    List<CartVO> getCart(String memberId);

}
