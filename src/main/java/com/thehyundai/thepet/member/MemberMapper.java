package com.thehyundai.thepet.member;

import org.apache.ibatis.annotations.Mapper;
import java.util.Optional;

@Mapper
public interface MemberMapper {
    Optional<MemberVO> findMemberById(Integer id);
    Optional<MemberVO> login(Long socialId);
    Integer register(MemberVO member);
}
