package com.thehyundai.thepet.domain.backoffice.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackOfficeMemberMapper {

    List<BackOfficeMemberVO> selectAllMember(Integer page);
    List<BackOfficeMemberVO> selectHeendyMember(Integer page);
    List<BackOfficeMemberVO> selectSubscribeMember(Integer page);
    List<BackOfficeMemberVO> selectDeliveryMember(Integer page);

    List<BackOfficeMemberVO> selectAllDateMember(MemberRequestVO req);
    List<BackOfficeMemberVO> selectHeendyDateMember(MemberRequestVO req);
    List<BackOfficeMemberVO> selectSubscribeDateMember(MemberRequestVO req);
    List<BackOfficeMemberVO> selectDeliveryDateMember(MemberRequestVO req);

    Integer selectAllMemberCount();
    Integer selectHeendyMemberCount();
    Integer selectSubscribeMemberCount();
    Integer selectDeliveryMemberCount();

    Integer selectAllDateMemberCount(MemberRequestVO req);
    Integer selectHeendyDateMemberCount(MemberRequestVO req);
    Integer selectSubscribeDateMemberCount(MemberRequestVO req);
    Integer selectDeliveryDateMemberCount(MemberRequestVO req);
}
