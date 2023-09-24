package com.thehyundai.thepet.domain.backoffice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/backoffice")
@Tag(name = "Backoffice Controller", description = "백오피스 관련 컨트롤러")
public class BackofficeController {

     private final BackofficeService backofficeService;

    @GetMapping("/top10")
    @Operation(summary = "지난 달 매출 Top10인 상품들")
    public ResponseEntity<List<BackOfficeVO>> getTop10Products() {
        List<BackOfficeVO> Top10List = backofficeService.getTop10Products();
        return ResponseEntity.ok(Top10List);
    }

}
