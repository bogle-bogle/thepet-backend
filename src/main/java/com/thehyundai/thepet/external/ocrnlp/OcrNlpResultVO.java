package com.thehyundai.thepet.external.ocrnlp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OcrNlpResultVO {
    private String ingredients;
    private List<ProductSuggestionVO> suggestions;
    private String executionTime;
}
