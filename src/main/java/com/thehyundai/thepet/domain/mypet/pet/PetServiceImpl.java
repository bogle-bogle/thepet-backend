package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.global.util.EntityValidator;
import com.thehyundai.thepet.global.cmcode.CmCodeMapper;
import com.thehyundai.thepet.global.util.ProteinCmCode;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Log4j2
@Service
@RequiredArgsConstructor
@TimeTraceService
public class PetServiceImpl implements PetService {
    private final PetMapper petMapper;
    private final CmCodeMapper cmCodeMapper;
    private final AuthTokensGenerator authTokensGenerator;
    private final EntityValidator entityValidator;

    @Override
    public String registerClub(String token, PetVO petVO) {
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);
        petVO.setMemberId(memberId);
        petMapper.registerClub(petVO);
        return petVO.getId();
    }

    @Override
    public Integer updateFeed(PetVO petVO,String id) {
        petVO.setId(id);
        findFavoriteProteinCode(petVO).ifPresent(petVO::setFavoriteProteinCode);
        return petMapper.updateFeed(petVO);
    }

    @Override
    public List<PetVO> myPet(String memberId) {
        entityValidator.getPresentMember(memberId);
        List<PetVO> result = petMapper.myPet(memberId);
        return result;
    }

//    @Override
//    public List<CmCodeVO> getAllCode() {
//        return cmCodeMapper.getAllCode();
//    }

    @Override
    public List<PetVO> findPetsWithAllergies(String memberId) {
        List<PetVO> pets = petMapper.findPetsWithAllergiesByMemberId(memberId);
        return pets;
    }

    @Override
    public PetVO updateMbti(String petId, PetVO petVO) {
        petVO.setId(petId);
        if (petMapper.updateMbtiById(petVO) < 1) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return petVO;
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
