package com.thehyundai.thepet.domain.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO {
    private Integer detailId;
    private Integer orderId;
    private Integer cnt;
    private Integer productId;
    private String productName;
    private String productImgUrl;
    private Integer productPrice;
    private Integer curationId;
    private String curationName;
    private String curationImgUrl;
    private Integer curationPrice;
}