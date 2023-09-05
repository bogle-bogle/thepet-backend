package com.thehyundai.thepet.domain.order;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderDetailMapper {
    Integer saveOrderDetail(OrderDetailVO orderDetail);
}
