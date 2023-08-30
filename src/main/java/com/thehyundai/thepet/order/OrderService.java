package com.thehyundai.thepet.order;


import com.thehyundai.thepet.subscription.SubscriptionVO;

import java.util.List;

public interface OrderService {
    OrderVO orderWholeCart(String token);

    OrderVO createSubscriptionOrder(String token, SubscriptionVO requestVO);

    OrderVO createRegularDeliveryOrder(String token, SubscriptionVO requestVO);

    OrderVO showOrderWithDetails(Integer orderId);

    List<OrderVO> showAllMyOrdersWithDetails(String token);
}
