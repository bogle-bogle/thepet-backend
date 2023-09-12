package com.thehyundai.thepet.domain.subscription;

import com.thehyundai.thepet.domain.product.ProductVO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurationVO {
    private String id;
    private LocalDate paymentDate;
    private String product1Id;
    private String product2Id;
    private String product3Id;
    private Integer price;
    private String name;
    private String imgUrl;
    private String thumbnailImgUrl;
    private List<ProductVO> products;
}
