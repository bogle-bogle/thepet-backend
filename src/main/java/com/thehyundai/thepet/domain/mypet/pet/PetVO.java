package com.thehyundai.thepet.domain.mypet.pet;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
public class PetVO {
    private Integer id;
    private String photo;       // 컬럼명 수정 필요
    private String name;
    private LocalDate birth;
//    private String allergy;       // 삭제 필요
    private Integer memberId;
    private String favoriteFoodIngredients;
    private String imgUrl;       // 컬럼명 수정 필요
    private String mbti;
    private String breedCode;
    private String animalTypeCode;
    private String favoriteProteinCode;
    private String ageCode;
    private String sizeCode;
    private List<String> allergies;
}
