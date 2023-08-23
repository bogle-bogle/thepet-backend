package com.thehyundai.thepet.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public List<ProductVO> getAllProducts() {
        return productMapper.selectProducts();
    }

    @Override
    public ProductVO getProductDetail(int id) {
        return productMapper.selectProductDetail(id);
    }
}
