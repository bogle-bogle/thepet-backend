package com.thehyundai.thepet.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectProducts();
    ProductVO selectProductDetail(int id);
}
