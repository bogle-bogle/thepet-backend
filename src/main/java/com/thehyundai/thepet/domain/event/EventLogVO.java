package com.thehyundai.thepet.domain.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EventLogVO {
    private String page;
    private String element;
    private String itemId;
    private String isClicked;
}
