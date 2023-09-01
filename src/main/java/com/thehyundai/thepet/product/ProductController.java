package com.thehyundai.thepet.product;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/list/{page}")
    public ResponseEntity<ProductListVO> getProducts(@PathVariable int page) {
        return ResponseEntity.ok(productService.getAllProducts(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductVO> getProductDetail(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductDetail(id));
    }
}
