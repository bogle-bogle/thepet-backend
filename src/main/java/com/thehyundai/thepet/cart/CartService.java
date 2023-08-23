package com.thehyundai.thepet.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CartService {

    CartVO insertCart(CartVO cartVO);

    CartVO updateCart(CartVO cartVO);

    int deleteCart(int id);

    List<CartVO> getCart(int memberId);

}
