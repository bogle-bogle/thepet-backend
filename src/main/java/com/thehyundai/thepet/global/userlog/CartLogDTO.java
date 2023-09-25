package com.thehyundai.thepet.global.userlog;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartLogDTO {

    private String action;
    private String memberId;
    private String productId;
    private String createdAt;
    private String cartId;

}
