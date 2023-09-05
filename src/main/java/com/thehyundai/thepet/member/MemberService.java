package com.thehyundai.thepet.member;


import java.util.Optional;

public interface MemberService {
    MemberVO loginOrRegister(MemberVO member);

    Optional<MemberVO> showMember(Integer id);
}
