package com.thehyundai.thepet.domain.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product")
public class ProductController {
    private final ProductService productService;

    @PostMapping("/list/{page}")
    public ResponseEntity<ProductListVO> getProducts(@PathVariable int page, @RequestBody FilterVO filterVO) {
        log.info(filterVO);
        filterVO.setPage((page - 1) * 20 + 1);
        ProductListVO result = productService.getAllProducts(filterVO);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductDetail(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductDetail(id));
    }


}
