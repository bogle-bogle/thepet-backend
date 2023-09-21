package com.thehyundai.thepet.domain.heendycar;

import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;

@Log4j2
@RestController
//@TimeTraceController
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
    public ResponseEntity<?> createReservation(@RequestHeader(HEADER_TOKEN_PARAM) String token,  @RequestBody HcReservationVO requestVO) {
        HcReservationVO reservation = hcService.createReservation(token, requestVO);
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchCode}")
    @Operation(summary = "지점별 흰디카 잔여 개수 조회하기", description = "지점을 선택하면, 해당 지점 흰디카 정보를 불러옵니다.")
    public ResponseEntity<?> showBranchHeendyCar(@PathVariable String branchCode) {
        HcBranchVO result = hcService.showBranchInfo(branchCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchCode}/reservation")
    @Operation(summary = "지점별 흰디카 예약현황 조회하기", description = "지점을 선택하면, 해당 지점 흰디카 정보를 불러옵니다.")
    public ResponseEntity<?> showBranchReservation(@PathVariable String branchCode) {
        List<HcReservationVO> result = hcService.showBranchReservation(branchCode);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/myreservation")
    @Operation(summary = "나의 예약 내역 조회하기", description = "마이페이지에서 나의 흰디카 예약 내역을 모두 불러옵니다.")
    public ResponseEntity<?> showAllMyReservations(@RequestHeader(HEADER_TOKEN_PARAM) String token) {
        List<HcReservationVO> result = hcService.showAllMyReservations(token);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/{reservationId}")
    @Operation(summary = "흰디카 예약 취소하기", description = "나의 흰디카 예약 내역을 취소합니다.")
    public ResponseEntity<?> cancelHeendycarReservation(@RequestHeader(HEADER_TOKEN_PARAM) String token, @PathVariable String reservationId) {
        HcReservationVO result = hcService.cancelHeendycarReservation(reservationId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PutMapping("/updateStatus/{productId}/{type}/{newValue}")
    @Operation(summary = "관리자 흰디카 관리", description = "관리자의 흰디카 관리(반납, 예약, 픽업)")
    public ResponseEntity<?> adminHeendycarManage(@PathVariable String productId,@PathVariable String type,@PathVariable String newValue) {

        Integer result = hcService.adminHeendycarManage(productId,type,newValue);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}