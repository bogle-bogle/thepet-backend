package com.thehyundai.thepet.domain.member;


import com.thehyundai.thepet.domain.backoffice.member.BackOfficeMemberVO;

import java.util.List;
import java.util.Optional;

public interface MemberService {

    MemberVO loginOrRegister(MemberVO member);

    Optional<MemberVO> showMember(String id);

    MemberVO updateMemberInfo(MemberVO memberVO);

    MemberVO updateMemberBillingKey(MemberVO memberVO);

    MypageVO getMypageInfo(String token);

    MemberVO authToLogin(String code);
}
