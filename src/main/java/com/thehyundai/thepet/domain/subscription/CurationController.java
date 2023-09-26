package com.thehyundai.thepet.domain.subscription;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
//@ControllerTimeTrace
@RequiredArgsConstructor
@RequestMapping(value = "/api/curation")
public class CurationController {
    private final CurationService curationService;

    @PostMapping
    @Operation(summary = "월간 구독 상품 등록", description = "백오피스 상품 등록")
    public ResponseEntity<?> createGeneralProduct(@RequestBody CurationVO curationVO) {
        curationService.createCuration(curationVO);
        return new ResponseEntity<>(curationVO, HttpStatus.OK);
    }

    @GetMapping("/monthly")
    @Operation(summary = "이 달의 더펫박스 조회하기", description = "이번 달의 더펫박스 상세 정보를 불러옵니다.")    // API 정보 설정
    public ResponseEntity<?> showCurationOfCurrMonth() {
        CurationVO result = curationService.showCurationOfCurrMonth();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/annual")
    @Operation(summary = "지난 1년간의 더펫박스 조회하기", description = "지난 1년간의 더펫박스 상세 정보를 불러옵니다.")
    public ResponseEntity<?> showCurationOfLastOneYear() {
        List<CurationVO> result = curationService.showCurationOfLastOneYear();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/{curationId}")
    @Operation(summary = "더펫박스 상세 조회하기", description = "curationId를 이용하여 한 개의 더펫박스 상세 정보를 불러옵니다.")
    public ResponseEntity<?> showCurationDetail(@PathVariable String curationId) {
        CurationVO curation = curationService.showCurationDetail(curationId);
        return new ResponseEntity<>(curation, HttpStatus.OK);
    }
}
