package com.thehyundai.thepet.domain.backoffice.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackOfficeResMemberVO {
    private List<BackOfficeMemberVO> members;
    private Integer count;
}
