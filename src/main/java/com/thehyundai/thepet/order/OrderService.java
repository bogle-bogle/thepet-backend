package com.thehyundai.thepet.order;


import com.thehyundai.thepet.subscription.SubscriptionVO;

public interface OrderService {
    OrderVO createSubscriptionOrder(SubscriptionVO requestVO);
}
