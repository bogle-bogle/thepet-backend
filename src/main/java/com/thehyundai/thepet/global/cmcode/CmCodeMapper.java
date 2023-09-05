package com.thehyundai.thepet.global.cmcode;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface CmCodeMapper {
    Optional<CmCodeVO> findCmCodeByCodeValue(String codeValue);

    List<CmCodeVO> getAllCode();
}
