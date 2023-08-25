package com.thehyundai.thepet.order;

import com.thehyundai.thepet.subscription.SubscriptionVO;
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
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/cart")
    public ResponseEntity<?> orderWholeCart() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PostMapping("/curation")
    public ResponseEntity<?> createSubscriptionOrder(@RequestBody SubscriptionVO requestVO) {
        OrderVO result = orderService.createSubscriptionOrder(requestVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/regular-delivery")
    public ResponseEntity<?> createRegularDeliveryOrder(@RequestBody SubscriptionVO requestVO) {
        OrderVO result = orderService.createRegularDeliveryOrder(requestVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<?> showOrderDetail(@PathVariable Integer orderId) {
        OrderVO result = orderService.showOrderWithDetails(orderId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> showAllMyOrdersWithDetails() {
        List<OrderVO> result = orderService.showAllMyOrdersWithDetails(1);       // 일단 하드코딩 -----------------------------------
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
