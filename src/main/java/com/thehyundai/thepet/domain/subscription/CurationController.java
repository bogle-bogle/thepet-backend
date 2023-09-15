package com.thehyundai.thepet.domain.subscription;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
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

}
