package com.thehyundai.thepet.domain.heendycar;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HcReservationVO {
    private String id;
    private String branchCode;
    private String memberId;
    private LocalDateTime reservationTime;
    private LocalDateTime createdAt;
    private String pickupYn;
    private String cancelYn;
    private String returnYn;
    private String branchImgUrl;
    private String phoneNumber;
    private String name;
}
