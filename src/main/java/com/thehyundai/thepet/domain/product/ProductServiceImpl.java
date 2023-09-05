package com.thehyundai.thepet.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public ProductListVO getAllProducts(int page) {

        ProductListVO res = new ProductListVO();

        res.setProducts(productMapper.selectProducts((page - 1) * 20));
        log.info(res);
        res.setCount(productMapper.selectProductCount());
        log.info(res);
        return res;
    }

    @Override
    public ProductVO getProductDetail(int id) {
        return productMapper.selectProductDetail(id);
    }
}
