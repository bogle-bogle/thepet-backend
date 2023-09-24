package com.thehyundai.thepet.domain.mypet.pet;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetVO {
    private String id;
    private String petImgUrl;
    private String name;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate birth;
    private String memberId;
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