package com.thehyundai.thepet.domain.mypet.pet;


import com.thehyundai.thepet.external.ocrnlp.OcrNlpResultVO;

import java.util.List;

public interface PetService {

    String registerClub(String token,PetVO petVO);

    OcrNlpResultVO updateFeed(PetSuggestionRequestVO requestVO);

    List<PetVO> myPet(String memberId);

    List<PetVO> findPetsWithAllergies(String memberId);

    PetVO updateMbti(String petId, PetVO petVO);

    OcrNlpResultVO getSuggestions(String petId);
}
