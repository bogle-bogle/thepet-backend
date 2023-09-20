package com.thehyundai.thepet.global.event;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventLogMapper {
    void insertEventLog(EventLogVO eventLog);
}
