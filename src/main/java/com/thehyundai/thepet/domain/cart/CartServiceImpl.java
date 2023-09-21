package com.thehyundai.thepet.domain.cart;

import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
//@TimeTraceService
public class CartServiceImpl implements CartService {
    private final CartMapper cartMapper;

    @Override
    public CartVO insertCart(CartVO cartVO) {
        cartMapper.insertCart(cartVO);
        return cartVO;
    }

    @Override
    public CartVO updateCart(CartVO cartVO) {
        cartMapper.updateCart(cartVO);
        return cartVO;
    }

    @Override
    public String deleteCart(String id) {
        cartMapper.deleteCart(id);
        return id;
    }

    @Override
    public List<CartVO> getCart(String memberId) {
        return cartMapper.getCart(memberId);
    }


}

