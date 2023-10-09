package com.thehyundai.thepet.domain.backoffice.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRequestVO {
    private Integer page;
    private String startDate;
    private String endDate;
}
