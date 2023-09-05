package com.thehyundai.thepet.domain.member;


import java.util.Optional;

public interface MemberService {
    MemberVO loginOrRegister(MemberVO member);

    Optional<MemberVO> showMember(Integer id);
}
