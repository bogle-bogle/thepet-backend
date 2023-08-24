package com.thehyundai.thepet.member;

import com.thehyundai.thepet.util.AuthTokens;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
//@AllArgsConstructor
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

    @Builder
    public MemberVO(Integer id, Long socialId, String name, String email, String imgUrl, String address, String nickname, String clubHeendyYn, AuthTokens jwt) {
        this.id = id;
        this.socialId = socialId;
        this.name = name;
        this.email = email;
        this.imgUrl = imgUrl;
        this.address = address;
        this.nickname = nickname;
        this.clubHeendyYn = clubHeendyYn;
        this.jwt = jwt;
    }
}
