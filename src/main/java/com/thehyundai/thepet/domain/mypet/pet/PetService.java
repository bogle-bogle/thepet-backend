package com.thehyundai.thepet.domain.mypet.pet;


import com.thehyundai.thepet.global.cmcode.CmCodeVO;

import java.util.List;

public interface PetService {

    String registerClub(String token,PetVO petVO);

    Integer updateFeed(PetVO petVO,String id);

    List<PetVO> myPet(Integer memberId);

    List<CmCodeVO> getAllCode();

    List<PetVO> findPetsWithAllergies(String memberId);
}
