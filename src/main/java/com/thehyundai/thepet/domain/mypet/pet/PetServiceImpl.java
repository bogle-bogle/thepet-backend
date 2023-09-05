package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.global.CmCodeMapper;
import com.thehyundai.thepet.global.CmCodeVO;
import com.thehyundai.thepet.global.DataValidator;
import com.thehyundai.thepet.util.AuthTokensGenerator;
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
    private final AuthTokensGenerator authTokensGenerator;
    private final DataValidator dataValidator;

    @Override
    public Integer registerClub(String token, PetVO petVO) {
        Integer memberId = authTokensGenerator.extractMemberId(token);
        dataValidator.checkPresentMember(memberId);
        petVO.setMemberId(memberId);
        petMapper.registerClub(petVO);
        return petVO.getId();
    }

    @Override
    public Integer updateFeed(PetVO petVO) {
        return petMapper.updateFeed(petVO);
    }

    @Override
    public List<PetVO> myPet(String token) {
        Integer memberId = authTokensGenerator.extractMemberId(token);
        dataValidator.checkPresentMember(memberId);
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
