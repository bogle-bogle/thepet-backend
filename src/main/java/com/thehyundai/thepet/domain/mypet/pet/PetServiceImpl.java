package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.global.EntityValidator;
import com.thehyundai.thepet.global.cmcode.CmCodeMapper;
import com.thehyundai.thepet.global.cmcode.CmCodeVO;
import com.thehyundai.thepet.global.cmcode.ProteinCmCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetMapper petMapper;
    private final CmCodeMapper cmCodeMapper;
    private final AuthTokensGenerator authTokensGenerator;
    private final EntityValidator entityValidator;

    @Override
    public Integer registerClub(String token, PetVO petVO) {
        log.info(petVO);
        Integer memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);
        petVO.setMemberId(memberId);
        petMapper.registerClub(petVO);
        return petVO.getId();
    }

    @Override
    public Integer updateFeed(PetVO petVO,Integer id) {
        petVO.setId(id);
        findFavoriteProteinCode(petVO).ifPresent(petVO::setFavoriteProteinCode);
        return petMapper.updateFeed(petVO);
    }

    @Override
    public List<PetVO> myPet(String token) {
        Integer memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);
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

    private Optional<String> findFavoriteProteinCode(PetVO petVO) {
        List<String> ingredients = List.of(petVO.getFavoriteFoodIngredients().split(","));

        return ingredients.stream()
                          .map(this::getProteinCodeValue)
                          .filter(Optional::isPresent)
                          .map(Optional::get)
                          .findFirst();
    }

    private Optional<String> getProteinCodeValue(String ingredientName) {
        return Arrays.stream(ProteinCmCode.values())
                     .filter(protein -> ingredientName.contains(protein.getName()))
                     .map(ProteinCmCode::getCodeValue)
                     .findFirst();
    }

}
