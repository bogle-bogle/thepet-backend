package com.thehyundai.thepet.heendycar;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import com.thehyundai.thepet.global.DataValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.thehyundai.thepet.global.Constant.STATUS_N;

@Log4j2
@Service
@RequiredArgsConstructor
public class HeendyCarServiceImpl implements HeendyCarService {
    private final HeendyCarMapper heendyCarMapper;
    private final DataValidator dataValidator;

    @Override
    public List<BranchHeendyCarVO> showAllBranches() {
        List<BranchHeendyCarVO> result = heendyCarMapper.findAllBranches();
        return result;
    }

    @Override
    public HeendyCarReservationVO createReservation(HeendyCarReservationVO requestVO) {
//        dataValidator.checkPresentMember(requestVO.getMemberId());
        HeendyCarReservationVO reservation = HeendyCarReservationVO.builder()
                                                                   .branchCode(requestVO.getBranchCode())
                                                                   .memberId(1)     // 회원 하드코딩 -----------------------------------------
                                                                   .reservationTime(requestVO.getReservationTime())
                                                                   .createdAt(LocalDateTime.now())
                                                                   .pickupYn(STATUS_N)
                                                                   .cancelYn(STATUS_N)
                                                                   .returnYn(STATUS_N)
                                                                   .build();
        if (heendyCarMapper.saveReservation(reservation) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        handleAutoCancellation(reservation.getId());
        return reservation;
    }

    private void handleAutoCancellation(Integer reservationId) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
//        executor.schedule(() -> System.out.println("여기서 자동 삭제 실행 예정"), 30, TimeUnit.MINUTES);
        executor.schedule(() -> System.out.println("여기서 자동 삭제 실행 예정"), 30, TimeUnit.SECONDS);
        executor.shutdown();
    }
}
