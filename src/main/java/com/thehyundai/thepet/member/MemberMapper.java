package com.thehyundai.thepet.member;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    MemberVO login(Long socialId);
    void register(MemberVO member);
}
