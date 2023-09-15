package com.thehyundai.thepet.domain.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {
    Integer saveOrder(OrderVO order);
    Optional<OrderVO> getOrderWithOrderDetailsById(String orderId);
    List<OrderVO> showAllMyOrdersWithDetails(String memberId);
}
