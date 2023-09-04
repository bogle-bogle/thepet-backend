package com.thehyundai.thepet.heendycar;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import com.thehyundai.thepet.global.CmCodeValidator;
import com.thehyundai.thepet.global.DataValidator;
import com.thehyundai.thepet.util.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.thehyundai.thepet.global.Constant.STATUS_N;
import static com.thehyundai.thepet.global.Constant.STATUS_Y;

@Log4j2
@Service
@RequiredArgsConstructor
public class HcServiceImpl implements HcService {
    private final HcBranchMapper branchMapper;
    private final HcReservationMapper reservationMapper;
    private final DataValidator dataValidator;
    private final CmCodeValidator cmCodeValidator;
    private final AuthTokensGenerator authTokensGenerator;

    @Override
    public HcBranchVO showBranchInfo(String branchCode) {
        HcBranchVO result = branchMapper.findBranchInfoByBranchCode(branchCode)
                                        .orElseThrow(() -> new BusinessException(ErrorCode.CM_CODE_NOT_FOUND));
        return result;
    }

    @Override
    public List<HcBranchVO> showAllBranches() {
        List<HcBranchVO> result = branchMapper.findAllBranches();
        return result;
    }

    @Override
    public HcReservationVO createReservation(String token, HcReservationVO requestVO) {
        // 0. 유효성 검사 및 유저 검증
        validateRemainingCnt(requestVO);
        valiateAvailableTime(requestVO);
        Integer memberId = authTokensGenerator.extractMemberId(token);
        dataValidator.checkPresentMember(memberId);

        // 1. Reservation 생성
        HcReservationVO reservation = buildReservation(memberId, requestVO);

        // 2. RESERVATION 테이블에 INSERT
        if (reservationMapper.saveReservation(reservation) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. 30분 후에 픽업하지 않읋 시 자동 취소되는 스케줄러 생성
        handleAutoCancellation(reservation.getId());
        return reservation;
    }

    private void handleAutoCancellation(Integer reservationId) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> cancelIfNotPickedUp(reservationId), 30, TimeUnit.SECONDS);           // 30초 -> 30분으로 변경 예정
        executor.shutdown();
    }

    private void cancelIfNotPickedUp(Integer reservationId) {
        HcReservationVO reservation = reservationMapper.findReservationById(reservationId)
                .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
        if (reservation.getPickupYn().equals(STATUS_N)) {
            reservation.setCancelYn(STATUS_Y);
            if (reservationMapper.updateReservation(reservation) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
            log.info("취소 완료!");
        } else {
            log.info("이미 픽업됨!");
        }
    }

    private void validateRemainingCnt(HcReservationVO requestVO) {
        HcBranchVO branchInfo = branchMapper.findBranchInfoByBranchCode(requestVO.getBranchCode())
                                                      .orElseThrow(() -> new BusinessException(ErrorCode.CM_CODE_NOT_FOUND));
        if (branchInfo.getCnt() <= 0) throw new BusinessException(ErrorCode.NO_HEENDYCAR_AVAILABLE);
    }

    private void valiateAvailableTime(HcReservationVO requestVO) {
        if (requestVO.getReservationTime().isAfter(LocalDateTime.now().plusMinutes(30))) {
            throw new BusinessException(ErrorCode.NOT_AVAILABLE_RESERVATION_TIME);
        }
    }

    private HcReservationVO buildReservation(Integer memberId, HcReservationVO requestVO) {
        return HcReservationVO.builder()
                                     .branchCode(requestVO.getBranchCode())
                                     .memberId(memberId)
                                     .reservationTime(requestVO.getReservationTime())
                                     .createdAt(LocalDateTime.now())
                                     .pickupYn(STATUS_N)
                                     .cancelYn(STATUS_N)
                                     .returnYn(STATUS_N)
                                     .build();
    }
}
