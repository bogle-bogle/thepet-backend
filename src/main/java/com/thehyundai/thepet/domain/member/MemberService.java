package com.thehyundai.thepet.domain.member;


import java.util.List;
import java.util.Optional;

public interface MemberService {

    MemberVO loginOrRegister(MemberVO member);

    Optional<MemberVO> showMember(String id);

    List<BackOfficeMemberVO> getAllMember();

    List<BackOfficeMemberVO> getAllHeendyMember();

    List<BackOfficeMemberVO> getAllSubscribeMember();
    List<BackOfficeMemberVO> getAllDeliveryMember();

    MemberVO updateMemberInfo(MemberVO memberVO);

    MemberVO updateMemberBillingKey(MemberVO memberVO);

    MypageVO getMypageInfo(String token);

}
