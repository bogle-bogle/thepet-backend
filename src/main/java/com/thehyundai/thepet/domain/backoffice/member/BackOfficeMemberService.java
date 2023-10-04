package com.thehyundai.thepet.domain.backoffice.member;

import java.util.List;

public interface BackOfficeMemberService {

    List<BackOfficeMemberVO> getAllMember();

    List<BackOfficeMemberVO> getAllHeendyMember();

    List<BackOfficeMemberVO> getAllSubscribeMember();
    List<BackOfficeMemberVO> getAllDeliveryMember();
}
