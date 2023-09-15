package com.thehyundai.thepet.domain.mypet.pet;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AllergyVO {
    private String id;
    private String petId;
    private String proteinCode;
}
