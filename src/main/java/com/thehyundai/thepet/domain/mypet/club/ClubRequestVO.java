package com.thehyundai.thepet.domain.mypet.club;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubRequestVO {
    private MultipartFile petImgFile;
    private String name;
    private String birth;
    private String allergyCode;
    private String breedCode;
    private String animalTypeCode;
    private String sizeCode;
}
