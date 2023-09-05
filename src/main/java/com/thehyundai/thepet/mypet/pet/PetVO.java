package com.thehyundai.thepet.mypet.pet;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class PetVO {

    private Integer id;
    private String petImgUrl;
    private String name;
    private String birth;
    private Integer memberId;
    private String favoriteFoodIngredients;
    private String feedMainImgUrl;
    private String feedDescImgUrl;
    private String mbti;
    private String breedCode;
    private String animalTypeCode;
    private String sizeCode;
    private String favoriteProteinCode;


    //한번에 알러지까지 사용하기 위해
    private String allergyCode;

}
