package com.thehyundai.thepet.order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper {
    Integer saveOrder(OrderVO order);
    OrderVO getOrderWithOrderDetailsById(Integer orderId);
}
