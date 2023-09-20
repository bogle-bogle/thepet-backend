package com.thehyundai.thepet.domain.member;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
public class MypageVO {
    private Integer myPetCnt;
    private final Integer onDeliveryCnt = 0;
    private Integer subscriptionCnt;
    private final Integer couponCnt = 3;

    public MypageVO(Integer myPetCnt, Integer subscriptionCnt) {
        this.myPetCnt = myPetCnt;
        this.subscriptionCnt = subscriptionCnt;
    }
}
