package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.mypet.pet.PetVO;
import com.thehyundai.thepet.domain.product.ProductVO;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RecommendationVO {
    private PetVO petInfo;
    private List<ProductVO> products;
}
