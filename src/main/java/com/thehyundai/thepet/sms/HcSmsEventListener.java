package com.thehyundai.thepet.sms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.thehyundai.thepet.heendycar.HcBranchVO;
import com.thehyundai.thepet.heendycar.HcReservationVO;
import com.thehyundai.thepet.heendycar.HcService;
import com.thehyundai.thepet.member.MemberService;
import com.thehyundai.thepet.member.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

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

        Optional<MemberVO> memberInfo = memberService.showMember(reservation.getMemberId());
        HcBranchVO branch = hcService.showBranchInfo(reservation.getBranchCode());

        String memberName = memberInfo.get().getName();
        String memberPhoneNumber = memberInfo.get().getPhoneNumber();
        String branchName = branch.getName();
        LocalDateTime reservationTime = reservation.getReservationTime();
        String formattedReservationTime = reservationTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));


        String content = memberName + " 고객님, 반려동물 트롤리 흰디카 예약이 완료되었습니다.\n" +
                        "\n" +
                        "예약 지점 : " + branchName + "\n" +
                        "예약 시간 : " + formattedReservationTime + "\n" +
                        "\n" +
                        "흰디카를 30분 이내로 픽업하지 않으실 경우, 예약이 자동으로 취소될 수 있으니 유의해주세요.\n" +
                        "반려동물과 편안한 시간을 " + branchName + "에서 즐겨보세요!";

        String subject = "[ " +branchName + " 흰디카 픽업 예약 ]";

        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setTo(memberPhoneNumber);
        messageDTO.setContent(content);
        messageDTO.setSubject(subject);

        try {
            smsService.sendSms(messageDTO);
            log.info("이벤트 리스너임" + messageDTO);
        } catch (JsonProcessingException | URISyntaxException | InvalidKeyException | NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
