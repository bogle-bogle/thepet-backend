package com.thehyundai.thepet.global.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventLogVO {
    private String memberId;
    private String eventPage;
    private String event;
    private String eventSuccess;
    private String reason;
}
