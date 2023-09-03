package com.thehyundai.thepet.mypet.pet;


import lombok.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PetSimpleVO {
    private Integer petId;
    private String petName;
    private List<String> allergies;
}