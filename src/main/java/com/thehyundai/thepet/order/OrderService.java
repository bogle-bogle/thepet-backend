package com.thehyundai.thepet.order;


import com.thehyundai.thepet.subscription.SubscriptionVO;

public interface OrderService {
    OrderVO createSubscriptionOrder(SubscriptionVO requestVO);

    OrderVO createRegularDeliveryOrder(SubscriptionVO requestVO);

    OrderVO showOrderWithDetails(Integer orderId);
}
