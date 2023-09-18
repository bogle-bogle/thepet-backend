package com.thehyundai.thepet.domain.member;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<MemberVO> findMemberById(String id);
    Optional<MemberVO> findMemberBySocialId(Long socialId);
    Integer register(MemberVO member);

    List<MemberVO> selectAllMember();
    List<MemberVO> selectHeendyMember();
}
