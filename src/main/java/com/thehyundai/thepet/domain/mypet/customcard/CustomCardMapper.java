package com.thehyundai.thepet.domain.mypet.customcard;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomCardMapper {
    Integer saveCustomCardDesign(CustomCardVO customCard);
}
