package com.thehyundai.thepet.domain.backoffice.eventlog;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backoffice")
@Tag(name = "Backoffice EventLog Controller", description = "백오피스 이벤트 로그 컨트롤러")
public class EventLogController {


}
