package com.thehyundai.thepet.domain.product;


public interface ProductService {

    ProductVO createGeneralProduct(ProductVO productVO);

//    public List<ProductVO> getAllProducts();
    ProductListVO getAllProducts(FilterVO filterVO);
    ProductVO getProductDetail(String id);
}
