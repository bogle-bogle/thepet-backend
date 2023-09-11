package com.thehyundai.thepet.domain.product;


public interface ProductService {
//    public List<ProductVO> getAllProducts();
    public ProductListVO getAllProducts(FilterVO filterVO);
    public ProductVO getProductDetail(int id);
}
