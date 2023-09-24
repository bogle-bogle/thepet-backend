package com.thehyundai.thepet.domain.mypet.pet;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PetSuggestionRequestVO {
    private String petId;
    private MultipartFile feedMainImgFile;
    private MultipartFile feedDescImgFile;
}
