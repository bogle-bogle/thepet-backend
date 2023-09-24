package com.thehyundai.thepet.domain.cart;

import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
//@TimeTraceController
@RequiredArgsConstructor
@RequestMapping(value = "/api/cart")
public class CartController {
    private final CartService cartService;


    @PostMapping
    public ResponseEntity<CartVO> insertCart(@RequestBody CartVO cartVO) {
        cartService.insertCart(cartVO);
//        CartLogDTO cartLogDTO = CartLogDTO.builder()
//                .action("add_to_cart")
//                .memberId(cartVO.getMemberId())
//                .productId(cartVO.getProductId())
//                .createdAt(cartVO.getCreatedAt())
//                .cartId(cartVO.getId())
//                .build();

        return ResponseEntity.ok(cartVO);
    }

    @PutMapping
    public ResponseEntity<CartVO> updateCart(@RequestBody CartVO cartVO) {
        cartService.updateCart(cartVO);
        return ResponseEntity.ok(cartVO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> deleteCart(@PathVariable String id) {
        cartService.deleteCart(id);
        return ResponseEntity.ok(id);
    }

    @GetMapping("/{memberId}")
    public ResponseEntity<List<CartVO>> getCart(@PathVariable String memberId) {
        List<CartVO> cartList = cartService.getCart(memberId);
        return ResponseEntity.ok(cartList);
    }

}
