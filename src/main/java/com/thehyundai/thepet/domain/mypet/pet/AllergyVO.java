package com.thehyundai.thepet.domain.mypet.pet;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AllergyVO {
    private Integer id;
    private Integer petId;
    private String proteinCode;
}
