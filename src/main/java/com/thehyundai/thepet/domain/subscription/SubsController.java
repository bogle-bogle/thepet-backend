package com.thehyundai.thepet.domain.subscription;


import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "Subscription Controller", description = "구독 관련 컨트롤러")    // Contoller 정보 설정
@RequestMapping("/api/sub")
public class SubsController {
    private final SubsService subsService;

    @GetMapping("/curation/monthly")
    @TimeTraceController
    @Operation(summary = "이 달의 더펫박스 조회하기", description = "이번 달의 더펫박스 상세 정보를 불러옵니다.")    // API 정보 설정
    public ResponseEntity<?> showCurationOfCurrMonth() {
        CurationVO result = subsService.showCurationOfCurrMonth();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curation/annual")
    @TimeTraceController
    @Operation(summary = "지난 1년간의 더펫박스 조회하기", description = "지난 1년간의 더펫박스 상세 정보를 불러옵니다.")
    public ResponseEntity<?> showCurationOfLastOneYear() {
        List<CurationVO> result = subsService.showCurationOfLastOneYear();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/curation/{curationId}")
    @TimeTraceController
    @Operation(summary = "더펫박스 상세 조회하기", description = "curationId를 이용하여 한 개의 더펫박스 상세 정보를 불러옵니다.")
    public ResponseEntity<?> showCurationDetail(@PathVariable String curationId) {
        CurationVO curation = subsService.showCurationDetail(curationId);
        return new ResponseEntity<>(curation, HttpStatus.OK);
    }
}
