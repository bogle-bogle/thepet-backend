package com.thehyundai.thepet.domain.heendycar;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HcReservationVO {
    private Integer id;
    private String branchCode;
    private Integer memberId;
    private LocalDateTime reservationTime;
    private LocalDateTime createdAt;
    private String pickupYn;
    private String cancelYn;
    private String returnYn;
}
