package com.thehyundai.thepet.domain.order;


import com.thehyundai.thepet.domain.subscription.SubscriptionVO;

import java.util.List;

public interface OrderService {
    OrderVO orderWholeCart(String token);

    OrderVO createSubscriptionOrder(String token, SubscriptionVO requestVO);

    OrderVO createRegularDeliveryOrder(String token, SubscriptionVO requestVO);

    OrderVO showOrderWithDetails(String orderId);

    List<OrderVO> showAllMyOrdersWithDetails(String token);
}
