package com.thehyundai.thepet.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface OrderMapper {
    Integer saveOrder(OrderVO order);
    Optional<OrderVO> getOrderWithOrderDetailsById(Integer orderId);
    List<OrderVO> showAllMyOrdersWithDetails(Integer memberId);
}
