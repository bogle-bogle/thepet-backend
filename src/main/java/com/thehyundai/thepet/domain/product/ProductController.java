package com.thehyundai.thepet.domain.product;

import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Log4j2
@RestController
@TimeTraceController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/general")
    @Operation(summary = "일반 상품 등록", description = "백오피스 상품 등록")
    public ResponseEntity<?> createGeneralProduct(@RequestBody ProductVO productVO) {
        productService.createGeneralProduct(productVO);
        return new ResponseEntity<>(productVO, HttpStatus.OK);
    }

    @GetMapping("/search")
    @Operation(summary = "카테고리와 상품명으로 상품 검색", description = "백오피스 구독 상품 등록")
    public ResponseEntity<?> searchProductsForCuration(@RequestParam(name="main-category") String mainCategory,
                                                       @RequestParam(name="keyword") String keyword) {
        Map<String, String> params = new HashMap<>();
        params.put("main-category", mainCategory);
        params.put("keyword", keyword);

        List<ProductVO> result = productService.searchProducts(params);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/list/{page}")
    public ResponseEntity<ProductListVO> getProducts(@PathVariable int page, @RequestBody FilterVO filterVO) {
        filterVO.setPage((page - 1) * 20 + 1);
        ProductListVO result = productService.getAllProducts(filterVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductDetail(@PathVariable String id) {
        ProductVO result = productService.getProductDetail(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
