package com.thehyundai.thepet.domain.mypet.pet;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class PetVO{
    private Integer id;
    private String petImgUrl;
    private String name;
    private LocalDate birth;
    private Integer memberId;
    private String favoriteFoodIngredients;
    private String feedMainImgUrl;
    private String feedDescImgUrl;
    private String mbti;
    private String breedCode;
    private String animalTypeCode;
    private String sizeCode;
    private String ageCode;
    private String favoriteProteinCode;
    private List<String> allergies;

    private String allergyCode;    // 최초 등록용 값
}