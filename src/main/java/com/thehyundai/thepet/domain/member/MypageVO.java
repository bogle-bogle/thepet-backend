package com.thehyundai.thepet.domain.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MypageVO {
    private Integer myPetCnt;
    private Integer onDeliveryCnt = 0;
    private Integer subscriptionCnt;
    private Integer couponCnt = 3;

    public MypageVO(Integer myPetCnt, Integer subscriptionCnt) {
        this.myPetCnt = myPetCnt;
        this.onDeliveryCnt = 0;
        this.subscriptionCnt = subscriptionCnt;
        this.couponCnt = 3;
    }
}
