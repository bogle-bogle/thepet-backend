package com.thehyundai.thepet.domain.backoffice.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackOfficeMemberMapper {

    List<BackOfficeMemberVO> selectAllMember();
    List<BackOfficeMemberVO> selectHeendyMember();
    List<BackOfficeMemberVO> selectSubscribeMember();
    List<BackOfficeMemberVO> selectDeliveryMember();
}
