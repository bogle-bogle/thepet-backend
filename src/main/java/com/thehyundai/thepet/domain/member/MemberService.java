package com.thehyundai.thepet.domain.member;


import java.util.List;
import java.util.Optional;

public interface MemberService {
    MemberVO loginOrRegister(MemberVO member);

    Optional<MemberVO> showMember(String id);

    List<MemberVO> getAllMember();

    List<MemberVO> getAllHeendyMember();
}
