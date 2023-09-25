package com.thehyundai.thepet.external.sms;

import com.thehyundai.thepet.domain.member.MemberService;
import com.thehyundai.thepet.domain.member.MemberVO;
import com.thehyundai.thepet.domain.order.OrderService;
import com.thehyundai.thepet.domain.order.OrderVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.StringJoiner;

@Log4j2
@RequiredArgsConstructor
@Component
public class PaymentSmsEventListener implements ApplicationListener<PaymentSmsEvent> {

    private final MemberService memberService;
    private final OrderService orderService;
    private final SmsService smsService;

    private static final String SUBJECT_FORMAT = "[ THE PET 결제 완료 ]";
    @Override
    public void onApplicationEvent(PaymentSmsEvent event) {
        OrderVO order = event.getOrder();

        MemberVO memberInfo = memberService.showMember(order.getMemberId())
                .orElseThrow(() -> new BusinessException(ErrorCode.MEMBER_NOT_FOUND));

        OrderVO orderDetail = orderService.showOrderWithDetails(order.getId());

        MessageDTO messageDTO;
        messageDTO = createMessageDTO(memberInfo, orderDetail);

        try {
            smsService.sendSms(messageDTO);
        } catch (Exception e) {
            log.error("SMS 전송 실패 : " + e.getMessage());
            throw new BusinessException(ErrorCode.SMS_ERROR);
        }
    }

    private MessageDTO createMessageDTO(MemberVO memberInfo, OrderVO orderDetail) {
        // 0. 휴대폰번호 유효성 검증
        validatePhoneNumber(memberInfo.getPhoneNumber());

        // 1. SMS 제목 및 내용 생성
        String smsTitle = String.format(SUBJECT_FORMAT);
        String smsContent = generateOrderCompleteSmsContent(memberInfo, orderDetail);

        // 2. SMS 생성
        return MessageDTO.builder()
                .to(memberInfo.getPhoneNumber())
                .subject(smsTitle)
                .content(smsContent)
                .build();
    }

    private String generateOrderCompleteSmsContent(MemberVO memberInfo, OrderVO orderDetail) {

        String firstProductName = orderDetail.getOrderDetails().isEmpty() ? "THE PET" :
                orderDetail.getOrderDetails().get(0).getProductName();

        if (firstProductName.length() >= 10) {
            firstProductName = firstProductName.substring(0, 10) + "...";
        }

        return new StringJoiner("\n")
                .add(memberInfo.getName() + " 고객님, 상품 주문이 완료되었습니다.")
                .add("")
                .add("주문번호 : " + orderDetail.getId())
                .add("주문 건 : " + firstProductName + "외 " + (orderDetail.getTotalCnt()-1) + "건")
                .add("")
                .add("■ 2~3일 후 배송이 완료됩니다. 감사합니다.")

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
