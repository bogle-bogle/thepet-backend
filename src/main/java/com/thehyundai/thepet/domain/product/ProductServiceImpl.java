package com.thehyundai.thepet.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public ProductListVO getAllProducts(int page, FilterVO filterVO) {

        ProductListVO res = new ProductListVO();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("animal_list", filterVO.getAnimalFilter());
        map.put("product_sub_list", filterVO.getProductSubFilter());
        map.put("protein_list", filterVO.getProteinFilter());
        map.put("page", page);
        res.setProducts(productMapper.filterProduct(map));
        res.setCount(productMapper.selectProductCount(map));
        return res;
    }

    @Override
    public ProductVO getProductDetail(int id) {
        return productMapper.selectProductDetail(id);
    }
}
