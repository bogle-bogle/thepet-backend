package com.thehyundai.thepet.domain.order;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderVO {
    private String id;
    private Integer totalCnt;
    private Integer totalPrice;
    private LocalDate createdAt;
    private String memberId;
    private String subscribeYn;

    private List<OrderDetailVO> orderDetails;
}
