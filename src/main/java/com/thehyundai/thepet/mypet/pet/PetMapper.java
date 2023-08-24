package com.thehyundai.thepet.mypet.pet;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PetMapper {

    Integer registerClub(PetVO petVO);

    Integer updateFeed(PetVO petVO);
}
