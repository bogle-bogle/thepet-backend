package com.thehyundai.thepet.domain.cart;

import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cart")
public class CartController {
    private final CartService cartService;

    @PostMapping
    @TimeTraceController
    public ResponseEntity<CartVO> insertCart(@RequestBody CartVO cartVO) {
        cartService.insertCart(cartVO);
        return ResponseEntity.ok(cartVO);
    }

    @PatchMapping
    @TimeTraceController
    public ResponseEntity<CartVO> updateCart(@RequestBody CartVO cartVO) {
        cartService.updateCart(cartVO);
        return ResponseEntity.ok(cartVO);
    }

    @DeleteMapping("/{id}")
    @TimeTraceController
    public ResponseEntity<String> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{memberId}")
    @TimeTraceController
    public ResponseEntity<List<CartVO>> getCart(@PathVariable String memberId) {
        List<CartVO> cartList = cartService.getCart(memberId);
        return ResponseEntity.ok(cartList);
    }

}
