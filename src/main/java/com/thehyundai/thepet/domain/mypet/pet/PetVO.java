package com.thehyundai.thepet.domain.mypet.pet;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class PetVO {
    private Integer id;
    private String photo;
    private String name;
    private LocalDate birth;
    private String allergy;
    private Integer memberId;
    private String favoriteFoodIngredients;
    private String imgUrl;
    private String mbti;
    private String breedCode;
    private String animalTypeCode;
    private String favoriteProteinCode;
    private String ageCode;
    private String sizeCode;
    private List<String> allergies;
}
