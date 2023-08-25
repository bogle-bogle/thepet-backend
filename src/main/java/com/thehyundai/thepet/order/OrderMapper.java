package com.thehyundai.thepet.order;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    Integer saveOrder(OrderVO order);
    OrderVO getOrderWithOrderDetailsById(Integer orderId);
    List<OrderVO> showAllMyOrdersWithDetails(Integer memberId);
}
