package com.thehyundai.thepet.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private Integer id;
    private String name;
    private Integer age;
    private String email;
    private String imgUrl;
    private String address;
    private String nickname;
    private Integer clubHeendy;
}
