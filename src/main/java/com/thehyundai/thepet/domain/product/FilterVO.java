package com.thehyundai.thepet.domain.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilterVO {
    private String mainFilter;
    private List<String> subFilter;
    private List<String> proteinFilter;
    private Integer page;
}
