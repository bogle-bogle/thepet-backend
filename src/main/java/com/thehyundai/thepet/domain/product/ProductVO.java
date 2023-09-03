package com.thehyundai.thepet.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
    private Integer id;
    private String name;
    private Integer price;
    private String mainImgUrl;
    private String descImgUrl;
    private String ingredients;
    private String mainCategoryCode;
    private String subCategoryCode;
    private String animalTypeCode;
    private String proteinCode;
}
