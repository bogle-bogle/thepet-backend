package com.thehyundai.thepet.member;

import com.thehyundai.thepet.util.AuthTokens;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
