package com.thehyundai.thepet.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterVO {

    private List<String> productSubFilter;
    private List<String> animalFilter;
    private List<String> proteinFilter;
}
