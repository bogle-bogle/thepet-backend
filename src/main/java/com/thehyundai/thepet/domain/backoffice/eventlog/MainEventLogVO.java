package com.thehyundai.thepet.domain.backoffice.eventlog;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MainEventLogVO {
    private String element;
    private Integer count;
}
