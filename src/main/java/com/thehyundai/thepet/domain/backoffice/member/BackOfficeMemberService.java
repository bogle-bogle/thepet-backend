package com.thehyundai.thepet.domain.backoffice.member;

import java.util.List;

public interface BackOfficeMemberService {

    BackOfficeResMemberVO getAllMember(MemberRequestVO req);

    BackOfficeResMemberVO getAllHeendyMember(MemberRequestVO req);

    BackOfficeResMemberVO getAllSubscribeMember(MemberRequestVO req);
    BackOfficeResMemberVO getAllDeliveryMember(MemberRequestVO req);
}
