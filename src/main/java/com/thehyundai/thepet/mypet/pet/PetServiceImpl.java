package com.thehyundai.thepet.mypet.pet;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetMapper petMapper;

    @Override
    public Integer registerClub(PetVO petVO) {
        return petMapper.registerClub(petVO);
    }

    @Override
    public Integer updateFeed(PetVO petVO) {
        return petMapper.updateFeed(petVO);
    }
}
