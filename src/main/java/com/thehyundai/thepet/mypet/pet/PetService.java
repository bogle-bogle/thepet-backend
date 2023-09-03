package com.thehyundai.thepet.mypet.pet;


import com.thehyundai.thepet.global.CmCodeVO;

import java.util.List;

public interface PetService {

    Integer registerClub(String token,PetVO petVO);

    Integer updateFeed(PetVO petVO);

    List<PetVO> myPet(String token);

    List<CmCodeVO> getAllCode();
}
