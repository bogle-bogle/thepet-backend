package com.thehyundai.thepet.global.userlog;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderLogDTO {

    private String action;
    private String memberId;
    private String createdAt;
    private String orderDetailedId;
    private String totalPrice;
    private String Toss;

}
