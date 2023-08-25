package com.thehyundai.thepet.heendycar;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HeendyCarReservationVO {
    private Integer id;
    private String branchCode;
    private Integer memberId;
    private LocalDateTime reservationTime;
    private LocalDateTime createdAt;
    private String pickupYn;
    private String cancelYn;
    private String returnYn;
}
