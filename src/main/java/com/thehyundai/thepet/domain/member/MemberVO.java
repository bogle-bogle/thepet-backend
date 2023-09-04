package com.thehyundai.thepet.domain.member;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.thehyundai.thepet.global.jwt.AuthTokens;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberVO {
    private Integer id;
    private Long socialId;
    private String name;
    private String email;
    private String imgUrl;
    private String address;
    private String nickname;
    private String clubHeendyYn;
    private AuthTokens jwt;
}