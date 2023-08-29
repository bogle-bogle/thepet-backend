package com.thehyundai.thepet.global;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CmCodeMapper {
    Optional<CmCodeVO> findCmCodeByCodeValue(String codeValue);
}