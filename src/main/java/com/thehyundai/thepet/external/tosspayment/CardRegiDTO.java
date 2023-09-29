package com.thehyundai.thepet.external.tosspayment;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CardRegiDTO {
    private String customerKey;
    private String authKey;
}
