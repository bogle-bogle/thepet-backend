package com.thehyundai.thepet.domain.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderDetailVO {
    private String detailId;
    private String orderId;
    private Integer cnt;
    private String productId;
    private String productName;
    private String productImgUrl;
    private Integer productPrice;
    private String curationId;
    private String curationName;
    private String curationImgUrl;
    private Integer curationPrice;
}