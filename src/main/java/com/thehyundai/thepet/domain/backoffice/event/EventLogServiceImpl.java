package com.thehyundai.thepet.domain.backoffice.event;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventLogServiceImpl implements EventLogService{
    private final EventLogMapper eventLogMapper;

    @Override
    public Integer insertEventLog(EventLogVO eventLog) {
        return eventLogMapper.insertEventLog(eventLog);
    }
}
