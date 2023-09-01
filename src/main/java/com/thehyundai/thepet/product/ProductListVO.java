package com.thehyundai.thepet.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductListVO {
    private List<ProductVO> products;
    private Integer count;
}
