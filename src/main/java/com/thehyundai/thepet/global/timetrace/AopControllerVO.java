package com.thehyundai.thepet.global.timetrace;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class AopControllerVO {
    private String id;
    private LocalDate createdAt;
    private String requestMapping;
    private String methodName;
    private String parameterName;
    private String executionTime;
}