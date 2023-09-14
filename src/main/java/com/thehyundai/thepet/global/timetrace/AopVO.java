package com.thehyundai.thepet.global.timetrace;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AopVO {
    private String id;
    private LocalDate createdAt;
    private String requestName;
    private String methodName;
    private String executionTime;
    private String ifRedis;
}