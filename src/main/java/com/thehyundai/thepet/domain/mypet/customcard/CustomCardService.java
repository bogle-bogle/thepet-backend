package com.thehyundai.thepet.domain.mypet.customcard;

import org.springframework.web.multipart.MultipartFile;

public interface CustomCardService {
    CustomCardVO saveCustomCardDesign(String token, MultipartFile file);
}
