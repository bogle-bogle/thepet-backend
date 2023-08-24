package com.thehyundai.thepet.member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberVO {
    private Integer id;
    private String name;
    private String email;
    private String imgUrl;
    private String address;
    private String nickname;
    private String clubHeendyYn;
    private Long socialId;
}
