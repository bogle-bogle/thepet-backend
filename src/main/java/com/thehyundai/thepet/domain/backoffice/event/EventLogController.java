package com.thehyundai.thepet.domain.backoffice.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping(value = "/api")
public class EventLogController {

    private final EventLogService eventLogService;

    @PostMapping("/event")
    public ResponseEntity<Integer> insertClickEventLog(@RequestBody EventLogVO eventLog) {
        log.info(eventLog);
        return new ResponseEntity<>(eventLogService.insertEventLog(eventLog), HttpStatus.OK);
    }
}
