package com.thehyundai.thepet.domain.cart;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CartMapper {

    void insertCart(CartVO cartVO);

    void updateCart(CartVO cartVO);

    Integer deleteCart(String id);

    List<CartVO> getCart(String memberId);


}
