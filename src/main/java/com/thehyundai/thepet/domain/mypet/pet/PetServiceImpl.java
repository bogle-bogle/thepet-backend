package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.global.cmcode.CmCodeMapper;
import com.thehyundai.thepet.global.cmcode.CmCodeVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetMapper petMapper;
    private final CmCodeMapper cmCodeMapper;

    @Override
    public Integer registerClub(PetVO petVO) {
        petMapper.registerClub(petVO);
        return petVO.getId();
    }

    @Override
    public Integer updateFeed(PetVO petVO) {
        return petMapper.updateFeed(petVO);
    }

    @Override
    public List<PetVO> myPet(int memberId) {
        return petMapper.myPet(memberId);
    }

    @Override
    public List<CmCodeVO> getAllCode() {
        return cmCodeMapper.getAllCode();
    }

    @Override
    public List<PetVO> findPetsWithAllergies(Integer memberId) {
        List<PetVO> pets = petMapper.findPetsWithAllergiesByMemberId(memberId);
        return pets;
    }
}
