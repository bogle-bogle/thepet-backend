package com.thehyundai.thepet.domain.backoffice.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BackOfficeMemberVO {
    private String id;
    private String name;
    private String email;
    private String address;
    private String nickname;
    private String phoneNumber;
    private Date startDate;
    private Date endDate;
    private String clubHeendyYn;
    private String productId;
}
