package com.thehyundai.thepet.domain.order;


import com.thehyundai.thepet.domain.subscription.SubscriptionVO;

import java.util.List;

public interface OrderService {
    OrderVO orderWholeCart(String token, String tossOrderId);

    OrderVO createSubscriptionOrder(String token, SubscriptionVO requestVO);

    OrderVO createRegularDeliveryOrder(String token, SubscriptionVO requestVO);

    OrderVO showOrderWithDetails(Integer orderId);

    List<OrderVO> showAllMyOrdersWithDetails(String token);
}
