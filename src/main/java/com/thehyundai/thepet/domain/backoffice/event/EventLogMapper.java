package com.thehyundai.thepet.domain.backoffice.event;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EventLogMapper {
    Integer insertEventLog(EventLogVO eventLog);
}
