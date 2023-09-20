package com.thehyundai.thepet.domain.order;


import com.thehyundai.thepet.domain.cart.CartVO;
import com.thehyundai.thepet.domain.subscription.SubscriptionVO;

import java.util.List;
import java.util.Map;

public interface OrderService {

    OrderVO orderWholeCart(String token, String tossOrderId);

    OrderVO orderSelectedCart(String token, String tossOrderId, List<CartVO> selectedItems);

    OrderVO createSubscriptionOrder(String token, SubscriptionVO requestVO);

    OrderVO createRegularDeliveryOrder(String token, SubscriptionVO requestVO);

    OrderVO showOrderWithDetails(String orderId);

    List<OrderVO> showAllMyOrdersWithDetails(String token);

    List<OrderVO> showMyNormalOrdersWithDetails(String token);

    Map<String, List<OrderVO>> showMySubscriptionWithDetails(String token);
}
