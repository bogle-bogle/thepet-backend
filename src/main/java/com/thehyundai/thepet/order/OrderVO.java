package com.thehyundai.thepet.order;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private Integer id;
    private Integer totalCnt;
    private Integer totalPrice;
    private LocalDate createdAt;
    private Integer memberId;
    private String subscribeYn;

    private List<OrderDetailVO> orderDetails;
}
