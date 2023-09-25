package com.thehyundai.thepet.domain.heendycar;

import com.thehyundai.thepet.domain.member.MemberService;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.global.cmcode.CmCodeValidator;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.external.sms.HcSmsEvent;
import com.thehyundai.thepet.global.util.EntityValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.thehyundai.thepet.global.exception.ErrorCode.*;
import static com.thehyundai.thepet.global.util.Constant.TABLE_STATUS_N;
import static com.thehyundai.thepet.global.util.Constant.TABLE_STATUS_Y;

@Log4j2
@Service
@RequiredArgsConstructor
//@ServiceTimeTrace
public class HcServiceImpl implements HcService {
    private final HcBranchMapper branchMapper;
    private final HcReservationMapper reservationMapper;
    private final EntityValidator entityValidator;
    private final CmCodeValidator cmCodeValidator;
    private final AuthTokensGenerator  authTokensGenerator;
    private final MemberService memberService;
    private final ApplicationEventPublisher eventPublisher;


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
    @Transactional
    public HcReservationVO createReservation(String token, HcReservationVO requestVO) {
        // 0. 유효성 검사 및 유저 검증
        validateRemainingCnt(requestVO);
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);
        if (requestVO.getPhoneNumber().isEmpty()) {
            throw new BusinessException(NO_PHONE_NUMBER);
        }

        validatePresentReservation(memberId);

        // 1. 회원 정보 업데이트
        MemberVO memberVO = MemberVO.builder()
                                    .id(memberId)
                                    .phoneNumber(requestVO.getPhoneNumber())
                                    .build();
        memberService.updateMemberInfo(memberVO);

        // 2. Reservation 생성
        HcReservationVO reservation = buildReservation(memberId, requestVO);

        // 3. RESERVATION 테이블에 INSERT
        if (reservationMapper.saveReservation(reservation) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 4. 30분 후에 픽업하지 않읋 시 자동 취소되는 스케줄러 생성
        handleAutoCancellation(reservation.getId());

        // 5. 문자 전송 이벤트 발생
        if (reservation.getId() != null) {
            HcSmsEvent hcSmsEvent = new HcSmsEvent(this, reservation);
            eventPublisher.publishEvent(hcSmsEvent);
        }
        return reservation;
    }

    @Override
    public List<HcReservationVO> showAllMyReservations(String token) {
        // 0. 유효성 검사 및 유저 검증
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        // 1. 나의 모든 예약 내역 가져오기
        List<HcReservationVO> result = reservationMapper.showAllMyReservations(memberId);
        return result;
    }

    @Override
    public HcReservationVO cancelHeendycarReservation(String reservationId) {
        HcReservationVO reservation = reservationMapper.findReservationById(reservationId)
                                                       .orElseThrow(() -> new BusinessException(RESERVATION_NOT_FOUND));
        if (reservationMapper.cancelReservation(reservationId) == 0) throw new BusinessException(DB_QUERY_EXECUTION_ERROR);
        reservation.setCancelYn(TABLE_STATUS_Y);
        return reservation;
    }

    @Override
    public Integer adminHeendycarManage(String productId, String type,String newValue) {
        HcReservationVO reservationVO = new HcReservationVO();
        reservationVO.setId(productId);
        if ("pickupYn".equals(type)) {
            reservationVO.setPickupYn(newValue);
            return reservationMapper.changePickUp(reservationVO);
        } else if ("cancelYn".equals(type)) {
            reservationVO.setCancelYn(newValue);
            return reservationMapper.changeCancel(reservationVO);
        } else if ("returnYn".equals(type)) {
            reservationVO.setReturnYn(newValue);
            return reservationMapper.changeReturn(reservationVO);
        }
        return null;
    }

    @Override
    public List<HcReservationVO> showBranchReservation(String branchCode) {
        return reservationMapper.findBranchReservation(branchCode);
    }


    private void handleAutoCancellation(String reservationId) {
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> cancelIfNotPickedUp(reservationId), 3, TimeUnit.MINUTES);           // 3분 -> 30분으로 변경 예정
        executor.shutdown();
    }

    private void cancelIfNotPickedUp(String reservationId) {
        HcReservationVO reservation = reservationMapper.findReservationById(reservationId)
                                                       .orElseThrow(() -> new BusinessException(ErrorCode.RESERVATION_NOT_FOUND));
        if (reservation.getPickupYn().equals(TABLE_STATUS_N)) {
            reservation.setCancelYn(TABLE_STATUS_Y);
            if (reservationMapper.updateReservation(reservation) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
            log.info("픽업하지 않고 30분이 지나 자동 취소됨");

            // 문자 전송 이벤트 발생
            if (reservation.getId() != null) {
                HcSmsEvent hcSmsEvent = new HcSmsEvent(this, reservation);
                eventPublisher.publishEvent(hcSmsEvent);

            }
        } else {
            log.info("이미 픽업됨");
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
    private HcReservationVO buildReservation(String memberId, HcReservationVO requestVO) {
        return HcReservationVO.builder()
                                     .branchCode(requestVO.getBranchCode())
                                     .memberId(memberId)
                                     .reservationTime(requestVO.getReservationTime())
                                     .createdAt(LocalDateTime.now())
                                     .pickupYn(TABLE_STATUS_N)
                                     .cancelYn(TABLE_STATUS_N)
                                     .returnYn(TABLE_STATUS_N)
                                     .build();
    }

    private void validatePresentReservation(String memberId) {
        reservationMapper.findPresentReservation(memberId)
                         .ifPresent(reservation -> {
                             throw new BusinessException(RESERVATION_ALREADY_EXISTS);
                         });
    }

}
