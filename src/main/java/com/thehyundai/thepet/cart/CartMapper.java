package com.thehyundai.thepet.cart;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CartMapper {

    void insertCart(CartVO cartVO);

    void updateCart(CartVO cartVO);

    void deleteCart(int id);

    List<CartVO> getCart(int memberId);


}
