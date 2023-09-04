package com.thehyundai.thepet.domain.order;

import com.thehyundai.thepet.domain.subscription.SubscriptionVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Tag(name = "Order Controller", description = "흰디카 관련 컨트롤러")
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/cart")
    @Operation(summary = "장바구니 (전체) 일괄 주문하기", description = "장바구니에 담긴 모든 상품을 한번에 주문합니다.")
    public ResponseEntity<?> orderWholeCart(@RequestHeader("Authorization") String token) {
        OrderVO result = orderService.orderWholeCart(token);
        log.info(result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/curation")
    @Operation(summary = "더펫박스 바로 구독하기", description = "더펫박스(큐레이션) 구독을 바로 주문합니다.")
    public ResponseEntity<?> createSubscriptionOrder(@RequestHeader("Authorization") String token, @RequestBody SubscriptionVO requestVO) {
        OrderVO result = orderService.createSubscriptionOrder(token, requestVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/regular-delivery")
    @Operation(summary = "정기배송 구독하기", description = "선택한 상품을 정기배송 구독을 주문합니다.")
    public ResponseEntity<?> createRegularDeliveryOrder(@RequestHeader("Authorization") String token, @RequestBody SubscriptionVO requestVO) {
        OrderVO result = orderService.createRegularDeliveryOrder(token, requestVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    @Operation(summary = "특정 주문 내역 조회하기", description = "주문ID를 통해 해당 주문 내역을 조회합니다.")
    public ResponseEntity<?> showOrderDetail(@PathVariable Integer orderId) {
        OrderVO result = orderService.showOrderWithDetails(orderId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    @Operation(summary = "나의 주문 내역 전체 조회하기", description = "지금까지의 나의 주문내역을 전체 조회합니다.")
    public ResponseEntity<?> showAllMyOrdersWithDetails(@RequestHeader("Authorization") String token) {
        List<OrderVO> result = orderService.showAllMyOrdersWithDetails(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}