package com.thehyundai.thepet.order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    Integer saveOrderDetail(OrderDetailVO orderDetail);
}
