package com.thehyundai.thepet.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO {
    private int id;
    private String name;
    private int price;
    private String mainImgUrl;
    private String descImgUrl;
    private String ingredients;
    private int productCategoryCode;
    private int subCategoryCode;
    private String animalTypeCode;
    private String proteinCode;
}
