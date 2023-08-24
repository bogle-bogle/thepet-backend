package com.thehyundai.thepet.mypet.pet;


import java.util.List;

public interface PetService {

    Integer registerClub(PetVO petVO);

    Integer updateFeed(PetVO petVO);

    List<PetVO> myPet(int memberId);
}
