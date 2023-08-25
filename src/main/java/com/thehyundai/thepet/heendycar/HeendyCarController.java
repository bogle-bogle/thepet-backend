package com.thehyundai.thepet.heendycar;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
@Tag(name = "HeendyCar Controller", description = "흰디카 관련 컨트롤러")
@RequestMapping("/api/hc")
public class HeendyCarController {
    private final HeendyCarService heendyCarService;

    @GetMapping("/branch")
    @Operation(summary = "흰디카 대여 가능한 모든 지점 조회하기", description = "흰디카 예약을 위해, 해당하는 모든 지점 정보를 불러옵니다.")
    public ResponseEntity<?> showAllBranches() {
        List<BranchHeendyCarVO> result = heendyCarService.showAllBranches();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/reservation")
    @Operation(summary = "흰디카 예약하기", description = "날짜, 지점을 선택하여 흰디카 예약을 생성합니다.")
    public ResponseEntity<?> createReservation(@RequestBody HeendyCarReservationVO requestVO) {
        HeendyCarReservationVO reservation = heendyCarService.createReservation(requestVO);
        handleAutoCancellation(reservation.getId());
        return new ResponseEntity<>(reservation, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchId}")
    @Operation(summary = "지점별 흰디카 잔여 개수 조회하기", description = "지점을 선택하면, 해당 지점 흰디카 정보를 불러옵니다.")
    public ResponseEntity<?> showBranchHeendyCar(@PathVariable Integer branchId) {
        return null;
    }

    private void handleAutoCancellation(Integer reservationId) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.schedule(() -> System.out.println("여기서 자동 삭제 실행 예정"), 30, TimeUnit.MINUTES);
        executor.schedule(() -> System.out.println("여기서 자동 삭제 실행 예정"), 30, TimeUnit.SECONDS);
        executor.shutdown();
    }
}