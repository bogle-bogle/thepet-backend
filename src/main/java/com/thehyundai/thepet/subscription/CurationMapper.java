package com.thehyundai.thepet.subscription;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface CurationMapper {
    Optional<CurationVO> findCurationById(Integer id);
}
