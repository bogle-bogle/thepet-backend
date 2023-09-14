package com.thehyundai.thepet.global.timetrace;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AopMapper {
    Integer saveAOPTable(AopVO aopVO);
}
