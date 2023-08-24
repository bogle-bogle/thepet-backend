package com.thehyundai.thepet.order;

import com.thehyundai.thepet.subscription.SubscriptionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

//    @PostMapping("/regular-delivery")
//    public ResponseEntity<?> createRegularDeliveryOrder(@RequestBody SubscriptionVO requestVO) {
//        OrderVO result = orderService.createRegularDeliveryOrder(requestVO);
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
