package com.thehyundai.thepet.domain.product;


public interface ProductService {
//    public List<ProductVO> getAllProducts();
    public ProductListVO getAllProducts(int page);
    public ProductVO getProductDetail(int id);
}
