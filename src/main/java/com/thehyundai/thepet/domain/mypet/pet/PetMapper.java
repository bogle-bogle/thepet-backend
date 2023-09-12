package com.thehyundai.thepet.domain.mypet.pet;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface PetMapper {
    Integer registerClub(PetVO petVO);
    Integer updateFeed(PetVO petVO);
    List<PetVO> myPet(String memberId);
    List<PetVO> findPetsWithAllergiesByMemberId(String memberId);
    Optional<PetVO> findPetWithAllergiesById(String id);
}
