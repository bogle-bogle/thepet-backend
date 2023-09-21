package com.thehyundai.thepet.domain.event;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventLogMapper {
    Integer insertEventLog(EventLogVO eventLog);
}
