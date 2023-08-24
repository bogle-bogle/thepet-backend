package com.thehyundai.thepet.order;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailVO {
    private Integer id;
    private Integer orderId;
    private Integer cnt;
    private Integer productId;
    private String productName;
    private String productImgUrl;
    private Integer curationId;
    private String curationName;
    private String curationImgUrl;
    private Integer curationPrice;
}