package com.thehyundai.thepet.domain.backoffice.member;

import java.util.List;

public interface BackOfficeMemberService {

    BackOfficeResMemberVO getAllMember(Integer page);

    BackOfficeResMemberVO getAllHeendyMember(Integer page);

    BackOfficeResMemberVO getAllSubscribeMember(Integer page);
    BackOfficeResMemberVO getAllDeliveryMember(Integer page);
}
