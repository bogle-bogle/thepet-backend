package com.thehyundai.thepet.external.sms;

import com.thehyundai.thepet.domain.order.OrderVO;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;

@Getter
@Log4j2
public class PaymentSmsEvent extends ApplicationEvent {
    private OrderVO order;


    public PaymentSmsEvent(Object source, OrderVO order) {
        super(source);
        this.order = order;
        log.info("결제 SMS 이벤트 실행");
    }
}
