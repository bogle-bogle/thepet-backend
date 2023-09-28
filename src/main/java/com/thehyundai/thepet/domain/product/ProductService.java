package com.thehyundai.thepet.domain.product;


import java.util.List;
import java.util.Map;

public interface ProductService {

    List<ProductVO> searchProducts(Map<String, String> params);

    ProductVO createGeneralProduct(ProductVO productVO);

    ProductListVO getAllFilteredProducts(FilterVO filterVO);

    ProductVO getProductDetail(String id);

}
