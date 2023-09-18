package com.thehyundai.thepet.global.sms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SmsResponseDTO {
    private String requestId;
    private Date requestTime;
    private String statusCode;
    private String statusName;
}
