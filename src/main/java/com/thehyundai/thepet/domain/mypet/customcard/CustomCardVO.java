package com.thehyundai.thepet.domain.mypet.customcard;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomCardVO {
    private String id;
    private String memberId;
    private String imgUrl;
    private Timestamp createdAt;
}
