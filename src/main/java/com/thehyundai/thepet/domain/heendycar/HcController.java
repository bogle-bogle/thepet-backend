package com.thehyundai.thepet.domain.heendycar;

import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import com.thehyundai.thepet.sms.HcSmsEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
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
    private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/branch")
    @TimeTraceController
    @Operation(summary = "흰디카 대여 가능한 모든 지점 조회하기", description = "흰디카 예약을 위해, 해당하는 모든 지점 정보를 불러옵니다.")
    public ResponseEntity<?> showAllBranches() {
        List<HcBranchVO> result = hcService.showAllBranches();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    @TimeTraceController
    @Operation(summary = "흰디카 예약하기", description = "날짜, 지점을 선택하여 흰디카 예약을 생성합니다.")
    public ResponseEntity<?> createReservation(@RequestHeader("Authorization") String token,  @RequestBody HcReservationVO requestVO) {
        HcReservationVO reservation = hcService.createReservation(token, requestVO);

        if (reservation != null) {
            // 예약 문자 보내기 - 이벤트 발생
            log.info("예약하기 실행");
            HcSmsEvent hcSmsEvent = new HcSmsEvent(this, reservation);
            eventPublisher.publishEvent(hcSmsEvent);
        }
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchCode}")
    @TimeTraceController
    @Operation(summary = "지점별 흰디카 잔여 개수 조회하기", description = "지점을 선택하면, 해당 지점 흰디카 정보를 불러옵니다.")
    public ResponseEntity<?> showBranchHeendyCar(@PathVariable String branchCode) {
        HcBranchVO result = hcService.showBranchInfo(branchCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}