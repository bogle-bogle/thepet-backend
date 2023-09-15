package com.thehyundai.thepet.global.timetrace;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class AopServiceVO {
    private String id;
    private LocalDate createdAt;
    private String requestName;
    private String methodName;
    private String executionTime;
    private String ifRedis;
}