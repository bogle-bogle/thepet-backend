package com.thehyundai.thepet.heendycar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@Tag(name = "HeendyCar Controller", description = "흰디카 관련 컨트롤러")
@RequestMapping("/api/hc")
public class HcController {
    private final HcService hcService;

    @GetMapping("/branch")
    @Operation(summary = "흰디카 대여 가능한 모든 지점 조회하기", description = "흰디카 예약을 위해, 해당하는 모든 지점 정보를 불러옵니다.")
    public ResponseEntity<?> showAllBranches() {
        List<HcBranchVO> result = hcService.showAllBranches();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    @Operation(summary = "흰디카 예약하기", description = "날짜, 지점을 선택하여 흰디카 예약을 생성합니다.")
    public ResponseEntity<?> createReservation(@RequestHeader("Authorization") String token,  @RequestBody HcReservationVO requestVO) {
        log.info(token);
        HcReservationVO reservation = hcService.createReservation(token, requestVO);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchCode}")
    @Operation(summary = "지점별 흰디카 잔여 개수 조회하기", description = "지점을 선택하면, 해당 지점 흰디카 정보를 불러옵니다.")
    public ResponseEntity<?> showBranchHeendyCar(@PathVariable String branchCode) {
        HcBranchVO result = hcService.showBranchInfo(branchCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}