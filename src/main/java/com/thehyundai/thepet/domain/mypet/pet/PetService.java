package com.thehyundai.thepet.domain.mypet.pet;


import com.thehyundai.thepet.global.cmcode.CmCodeVO;

import java.util.List;

public interface PetService {

    Integer registerClub(PetVO petVO);

    Integer updateFeed(PetVO petVO);

    List<PetVO> myPet(int memberId);

    List<CmCodeVO> getAllCode();

    List<PetSimpleVO> findPetsWithAllergies(Integer memberId);
}
