package com.thehyundai.thepet.sms;

import com.thehyundai.thepet.domain.heendycar.HcBranchVO;
import com.thehyundai.thepet.domain.heendycar.HcReservationVO;
import com.thehyundai.thepet.domain.heendycar.HcService;
import com.thehyundai.thepet.domain.member.MemberService;
import com.thehyundai.thepet.domain.member.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.StringJoiner;

@Log4j2
@RequiredArgsConstructor
@Component
public class HcSmsEventListener implements ApplicationListener<HcSmsEvent> {
    private final MemberService memberService;
    private final HcService hcService;
    private final SmsService smsService;

    @Override
    public void onApplicationEvent(HcSmsEvent event) {
        HcReservationVO reservation = event.getReservation();

        Optional<MemberVO> memberInfoOpt = memberService.showMember(reservation.getMemberId());
        MemberVO memberInfo = memberInfoOpt.get();

        HcBranchVO branch = hcService.showBranchInfo(reservation.getBranchCode());
        MessageDTO messageDTO = createMessageDTO(memberInfo, branch, reservation);

        try {
            smsService.sendSms(messageDTO);
        } catch (Exception e) {
            log.error("SMS 전송", e);
            throw new RuntimeException(e);
        }
    }

private MessageDTO createMessageDTO(MemberVO memberInfo, HcBranchVO branch, HcReservationVO reservation) {
    String memberName = memberInfo.getName();
    String memberPhoneNumber = memberInfo.getPhoneNumber();
    String branchName = branch.getName();
    LocalDateTime reservationTime = reservation.getReservationTime();
    String formattedReservationTime = reservationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

    String subject = "[ " +branchName + " 흰디카 픽업 예약 ]";
    StringJoiner contentJoiner = new StringJoiner("\n");
    contentJoiner
            .add(memberName + " 고객님, 반려동물 트롤리 흰디카 예약이 완료되었습니다.")
            .add("")
            .add("예약 지점 : " + branchName)
            .add("예약 시간 : " + formattedReservationTime)
            .add("")
            .add("흰디카를 30분 이내로 픽업하지 않으실 경우, 예약이 자동으로 취소될 수 있으니 유의해주세요.")
            .add("반려동물과 편안한 시간을 " + branchName + "에서 즐겨보세요!");

    MessageDTO messageDTO = new MessageDTO();
    messageDTO.setTo(memberPhoneNumber);
    messageDTO.setContent(contentJoiner.toString());
    messageDTO.setSubject(subject);

    return messageDTO;
    }
}
