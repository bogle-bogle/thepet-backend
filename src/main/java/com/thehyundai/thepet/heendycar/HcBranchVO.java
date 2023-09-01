package com.thehyundai.thepet.heendycar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HcBranchVO {
    private String branchCode;
    private Integer cnt;
    private String name;
    private String description;
    private String imgUrl;
}
