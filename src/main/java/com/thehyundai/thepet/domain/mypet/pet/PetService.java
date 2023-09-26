package com.thehyundai.thepet.domain.mypet.pet;


import com.thehyundai.thepet.domain.mypet.club.ClubRequestVO;
import com.thehyundai.thepet.external.ocrnlp.OcrNlpResultVO;

import java.util.List;

public interface PetService {

    PetVO registerClub(String token, ClubRequestVO requestVO);

    OcrNlpResultVO updateFeed(PetSuggestionRequestVO requestVO);

    List<PetVO> myPet(String memberId);

    List<PetVO> findPetsWithAllergies(String memberId);

    PetVO updateMbti(String petId, PetVO petVO);

    OcrNlpResultVO getSuggestions(String petId);
}
