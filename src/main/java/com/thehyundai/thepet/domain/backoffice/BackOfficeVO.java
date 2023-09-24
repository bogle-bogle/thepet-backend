package com.thehyundai.thepet.domain.backoffice;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BackOfficeVO {

    Integer ranking;
    String productId;
    String productName;
    String mainImgUrl;
    String mainCategoryCode;
    Integer salesVolume;
    Integer salesAmount;


}
