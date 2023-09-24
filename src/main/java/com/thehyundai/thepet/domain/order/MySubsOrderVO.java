package com.thehyundai.thepet.domain.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

import static com.thehyundai.thepet.global.util.Constant.TABLE_STATUS_N;
import static com.thehyundai.thepet.global.util.Constant.TABLE_STATUS_Y;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MySubsOrderVO {
    private List<OrderVO> curationY;
    private List<OrderVO> curationN;

    public MySubsOrderVO(Map<String, List<OrderVO>> subsMap) {
        this.curationY = subsMap.get(TABLE_STATUS_Y);
        this.curationN = subsMap.get(TABLE_STATUS_N);
    }
}
