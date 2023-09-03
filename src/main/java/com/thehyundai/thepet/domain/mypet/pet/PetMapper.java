package com.thehyundai.thepet.domain.mypet.pet;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PetMapper {
    Integer registerClub(PetVO petVO);
    Integer updateFeed(PetVO petVO);
    List<PetVO> myPet(Integer memberId);
    List<PetVO> findPetsWithAllergiesByMemberId(Integer memberId);
    PetVO findPetWithAllergiesById(Integer id);
}
