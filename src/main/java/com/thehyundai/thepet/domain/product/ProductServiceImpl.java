package com.thehyundai.thepet.domain.product;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Log4j2
@Service
@RequiredArgsConstructor
@TimeTraceService
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;

    @Override
    public List<ProductVO> searchProducts(Map<String, String> params) {
        List<ProductVO> result = productMapper.findProductsByCategoryAndKeyword(params);
        if(result.isEmpty()) {
            throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        }
        return result;
    }

    @Override
    public ProductVO createGeneralProduct(ProductVO productVO) {
        if (productMapper.saveGeneralProduct(productVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return productVO;
    }

    @Override
    public ProductListVO getAllProducts(FilterVO filterVO) {
        ProductListVO res = new ProductListVO();
        res.setProducts(productMapper.filterProduct(filterVO));
        res.setCount(productMapper.selectProductCount(filterVO));
        return res;
    }

    @Override
    public ProductVO getProductDetail(String id) {
        return productMapper.selectProductDetail(id);
    }
}
