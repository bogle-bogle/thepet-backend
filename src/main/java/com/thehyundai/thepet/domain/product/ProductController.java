package com.thehyundai.thepet.domain.product;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
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

    @PostMapping("/list/{page}")
    @Cacheable(value="productCache",key = "'productList:' + #page")
    public ResponseEntity<ProductListVO> getProducts(@PathVariable int page, @RequestBody FilterVO filterVO) {
        log.info(filterVO);
        filterVO.setPage((page - 1) * 20 + 1);
        ProductListVO result = productService.getAllProducts(filterVO);

        return ResponseEntity.ok(result);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductDetail(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductDetail(id));
    }




}
