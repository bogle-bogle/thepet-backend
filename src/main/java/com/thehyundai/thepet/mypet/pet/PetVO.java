package com.thehyundai.thepet.mypet.pet;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PetVO {

    private Integer id;
    private String photo;
    private String name;
    private String birth;
    private String allergy;
    private Integer memberId;
    private String favoriteFoodIngredients;
    private String imgUrl;
    private String mbti;
    private String breedCode;
    private String animalTypeCode;
    private String proteinCode;

}
