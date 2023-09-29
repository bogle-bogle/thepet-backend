package com.thehyundai.thepet.domain.backoffice.statistics;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backoffice/event")
@Tag(name = "Backoffice EventLog Controller", description = "백오피스 이벤트 로그 컨트롤러")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/main")
    public ResponseEntity<MainEventLogVO> getMainLog() {
        return new ResponseEntity<>(statisticsService.getMainEventLog(), HttpStatus.OK);
    }

    @GetMapping("/recommend")
    public ResponseEntity<SuggestionEventLogVO> getRecommendLog() {
        return new ResponseEntity<>(statisticsService.getSuggestionEventLog(), HttpStatus.OK);
    }

    @GetMapping("/shop")
    public ResponseEntity<ProductRateVO> getShopLog() {
        log.info("asdkjhasd");
        return new ResponseEntity<>(statisticsService.getProductRateEventLog(), HttpStatus.OK);
    }
}
