package com.thehyundai.thepet.product;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ProductMapper {
    List<ProductVO> selectProducts();
    ProductVO selectProductDetail(int id);

    Optional<ProductVO> findProductById(Integer id);       // 위의 selectProductDetail과 같은 기능
}
