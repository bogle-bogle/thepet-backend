package com.thehyundai.thepet.domain.backoffice.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackOfficeMemberMapper {

    List<BackOfficeMemberVO> selectAllMember(Integer page);
    List<BackOfficeMemberVO> selectHeendyMember(Integer page);
    List<BackOfficeMemberVO> selectSubscribeMember(Integer page);
    List<BackOfficeMemberVO> selectDeliveryMember(Integer page);

    Integer selectAllMemberCount();
    Integer selectHeendyMemberCount();
    Integer selectSubscribeMemberCount();
    Integer selectDeliveryMemberCount();
}
