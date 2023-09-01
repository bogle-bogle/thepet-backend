package com.thehyundai.thepet.product;

import java.util.List;


public interface ProductService {
//    public List<ProductVO> getAllProducts();
    public ProductListVO getAllProducts(int page);
    public ProductVO getProductDetail(int id);
}
