package com.thehyundai.thepet.external.ocrnlp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductSuggestionVO {
    private String id;
    private String name;
    private Integer price;
    private String mainImgUrl;
    private String ingredients;
    private Double matchRate;
}
