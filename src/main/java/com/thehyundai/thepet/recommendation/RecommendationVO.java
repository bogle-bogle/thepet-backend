package com.thehyundai.thepet.recommendation;

import com.thehyundai.thepet.mypet.pet.PetVO;
import com.thehyundai.thepet.product.ProductVO;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationVO {
    private PetVO petInfo;
    private List<ProductVO> products;
}
