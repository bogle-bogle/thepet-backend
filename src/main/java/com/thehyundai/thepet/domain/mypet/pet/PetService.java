package com.thehyundai.thepet.domain.mypet.pet;


import java.util.List;

public interface PetService {

    String registerClub(String token,PetVO petVO);

    Integer updateFeed(PetVO petVO,String id);

    List<PetVO> myPet(String memberId);

    List<PetVO> findPetsWithAllergies(String memberId);

    PetVO updateMbti(String petId, PetVO petVO);
}
