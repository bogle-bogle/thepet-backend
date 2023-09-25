package com.thehyundai.thepet.domain.mypet.customcard;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomCardRequestVO {
    private MultipartFile frontImgFile;
    private MultipartFile backImgFile;
}
