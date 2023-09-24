package com.thehyundai.thepet.external.sms;

import com.thehyundai.thepet.domain.heendycar.HcReservationVO;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationEvent;

@Getter
@Log4j2
public class HcSmsEvent extends ApplicationEvent {
    private HcReservationVO reservation;

    public HcSmsEvent(Object source, HcReservationVO reservation) {
        super(source);
        this.reservation = reservation;
        log.info("이벤트 실행");
    }
}
