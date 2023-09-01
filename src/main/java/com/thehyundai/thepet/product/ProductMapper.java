package com.thehyundai.thepet.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectProducts(int page);
    ProductVO selectProductDetail(int id);
    Integer selectProductCount();

    Optional<ProductVO> findProductById(Integer id);       // 위의 selectProductDetail과 같은 기능
}
