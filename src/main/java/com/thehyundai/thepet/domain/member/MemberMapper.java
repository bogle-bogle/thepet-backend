package com.thehyundai.thepet.domain.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {

    Optional<MemberVO> findMemberById(String id);

    Optional<MemberVO> findMemberBySocialId(Long socialId);

    Integer register(MemberVO member);

    List<BackOfficeMemberVO> selectAllMember();
    List<BackOfficeMemberVO> selectHeendyMember();
    List<BackOfficeMemberVO> selectSubscribeMember();
    List<BackOfficeMemberVO> selectDeliveryMember();

    Integer updateMemberInfo(MemberVO memberVO);

    Integer updateMemberBillingKey(MemberVO memberVO);

}
