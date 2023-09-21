package com.thehyundai.thepet.global.sms;

import com.thehyundai.thepet.domain.heendycar.HcBranchVO;
import com.thehyundai.thepet.domain.heendycar.HcReservationVO;
import com.thehyundai.thepet.domain.heendycar.HcService;
import com.thehyundai.thepet.domain.member.MemberService;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.global.cmcode.TableStatus;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.StringJoiner;

@Log4j2
@RequiredArgsConstructor
@Component
public class HcSmsEventListener implements ApplicationListener<HcSmsEvent> {

    private final MemberService memberService;
    private final HcService hcService;
    private final SmsService smsService;

    private static final String SUBJECT_FORMAT = "[ %s 흰디카 픽업 예약 ]";
    private static final DateTimeFormatter RESERVATION_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Override
    public void onApplicationEvent(HcSmsEvent event) {
        HcReservationVO reservation = event.getReservation();

        MemberVO memberInfo = memberService.showMember(reservation.getMemberId())
                                           .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        HcBranchVO branch = hcService.showBranchInfo(reservation.getBranchCode());

        MessageDTO messageDTO;
        if (reservation.getCancelYn().equals(TableStatus.N.getValue())) {
            messageDTO = createMessageDTO(memberInfo, branch, reservation);
        } else {
            messageDTO = createCancelMessageDTO(memberInfo, branch, reservation);
        }

        try {
            smsService.sendSms(messageDTO);
        } catch (Exception e) {
            log.error("SMS 전송 실패 : " + e.getMessage());
            throw new BusinessException(ErrorCode.SMS_ERROR);
        }
    }

    private MessageDTO createMessageDTO(MemberVO memberInfo, HcBranchVO branch, HcReservationVO reservation) {

        // 0. 휴대폰번호 유효성 검증
        validatePhoneNumber(memberInfo.getPhoneNumber());

        // 1. SMS 제목 및 내용 생성
        String smsTitle = String.format(SUBJECT_FORMAT, branch.getName());
        String smsContent = generateSuccessSmsContent(memberInfo, branch, reservation);

        // 2. SMS 생성
        return MessageDTO.builder()
                         .to(memberInfo.getPhoneNumber())
                         .subject(smsTitle)
                         .content(smsContent)
                         .build();
    }

    private MessageDTO createCancelMessageDTO(MemberVO memberInfo, HcBranchVO branch, HcReservationVO reservation) {
        // 0. 휴대폰번호 유효성 검증
        validatePhoneNumber(memberInfo.getPhoneNumber());

        // 1. SMS 제목 및 내용 생성
        String smsTitle = String.format(SUBJECT_FORMAT, branch.getName());
        String smsContent = generateCancelSmsContent(memberInfo, branch, reservation);

        // 2. SMS 생성
        return MessageDTO.builder()
                .to(memberInfo.getPhoneNumber())
                .subject(smsTitle)
                .content(smsContent)
                .build();
    }


    private String generateSuccessSmsContent(MemberVO memberInfo, HcBranchVO branch, HcReservationVO reservation) {
        String formattedReservationTime = reservation.getReservationTime().format(RESERVATION_FORMATTER);

        return new StringJoiner("\n")
                .add(memberInfo.getName() + " 고객님, 반려동물 트롤리 흰디카 예약이 완료되었습니다.")
                .add("")
                .add("예약 지점 : " + branch.getName())
                .add("예약 시간 : " + formattedReservationTime)
                .add("")
                .add("흰디카를 30분 이내로 픽업하지 않으실 경우, 예약이 자동으로 취소될 수 있으니 유의해주세요.")
                .add("반려동물과 편안한 시간을 " + branch.getName() + "에서 즐겨보세요!")
                .toString();
    }

    private String generateCancelSmsContent(MemberVO memberInfo, HcBranchVO branch, HcReservationVO reservation) {
        String formattedReservationTime = reservation.getReservationTime().format(RESERVATION_FORMATTER);

        return new StringJoiner("\n")
                .add(memberInfo.getName() + " 고객님,")
                .add("")
                .add("예약이 30분 이내 픽업되지 않아, 다른 고객님들의 편의를 위해 자동으로 취소되었습니다.")
                .add("")
                .add("웹사이트 혹은 더펫 App에서 다시 한 번 예약해주세요.")
                .toString();
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) throw new BusinessException(ErrorCode.NO_PHONE_NUMBER);

        String pattern = "^(010|011|016|017|018|019)\\d{7,8}$";

        if (!phoneNumber.matches(pattern)) {
            throw new BusinessException(ErrorCode.INVALID_PHONE_NUMBER);
        }
    }

}
