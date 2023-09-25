package com.thehyundai.thepet.global.timetrace;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AopMapper {

    void saveAOPServiceTable(AopServiceVO aopserviceVO);
    void saveAOPControllerTable(AopControllerVO aopcontrollerVO);

}
