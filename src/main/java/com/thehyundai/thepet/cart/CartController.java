package com.thehyundai.thepet.cart;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    public ResponseEntity<CartVO> insertCart(@RequestBody CartVO cartVO) {
        cartService.insertCart(cartVO);
        return new ResponseEntity<>(cartVO, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<CartVO> updateCart(@RequestBody CartVO cartVO) {
        cartService.updateCart(cartVO);
        return new ResponseEntity<>(cartVO, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deleteCart(@PathVariable int id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<CartVO>> getCart(@PathVariable int memberId) {
        List<CartVO> cartList = cartService.getCart(memberId);
        return new ResponseEntity<>(cartList, HttpStatus.OK);
    }
}
