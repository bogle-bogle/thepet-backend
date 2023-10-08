package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.domain.mypet.club.ClubRequestVO;
import com.thehyundai.thepet.external.aws.AwsS3Service;
import com.thehyundai.thepet.external.ocrnlp.ImgRequestVO;
import com.thehyundai.thepet.external.ocrnlp.OcrNlpResultVO;
import com.thehyundai.thepet.external.ocrnlp.OcrNlpService;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.global.timetrace.ServiceTimeTrace;
import com.thehyundai.thepet.global.util.EntityValidator;
import com.thehyundai.thepet.global.util.ProteinCmCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.thehyundai.thepet.global.exception.ErrorCode.DB_QUERY_EXECUTION_ERROR;
import static com.thehyundai.thepet.global.exception.ErrorCode.INVALID_IMAGE_TO_OCR;

@Log4j2
@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetMapper petMapper;
    private final AwsS3Service awsS3Service;
    private final OcrNlpService ocrNlpService;
    private final AuthTokensGenerator authTokensGenerator;
    private final EntityValidator entityValidator;

    @Override
    public PetVO registerClub(String token, ClubRequestVO requestVO) {
        // 0. 유효성 검사 및 회원 조회
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        // 1. S3에 업로드
        String imgUrl = awsS3Service.uploadToPetProfileBucket(requestVO.getPetImgFile());

        // 2. DB 저장 후 리턴
        PetVO petVO = PetVO.builder()
                           .memberId(memberId)
                           .petImgUrl(imgUrl)
                           .name(requestVO.getName())
                           .birth(LocalDate.parse(requestVO.getBirth()))
                           .allergyCode(requestVO.getAllergyCode())
                           .breedCode(requestVO.getBreedCode())
                           .animalTypeCode(requestVO.getAnimalTypeCode())
                           .sizeCode(requestVO.getSizeCode())
                           .build();
        if (petMapper.registerClub(petVO) == 0) throw new BusinessException(DB_QUERY_EXECUTION_ERROR);
        return petVO;
    }

    @Override
    @ServiceTimeTrace
    public OcrNlpResultVO updateFeed(PetSuggestionRequestVO requestVO) {

        String petId = requestVO.getPetId();
        PetVO pet = petMapper.findPetById(petId)
                             .orElseThrow(() -> new BusinessException(ErrorCode.PET_NOT_FOUND));
        String feedMainImgUrl = (requestVO.getFeedMainImgFile() != null) ? (awsS3Service.uploadToFeedBucket(requestVO.getFeedMainImgFile())) : pet.getFeedMainImgUrl();
        String feedDescrImgUrl = (requestVO.getFeedDescImgFile() != null) ? (awsS3Service.uploadToFeedBucket(requestVO.getFeedDescImgFile())) : pet.getFeedDescImgUrl();

        ImgRequestVO imgRequestVO = new ImgRequestVO(feedDescrImgUrl);
        OcrNlpResultVO ocrNlpResult = ocrNlpService.analyzeImageAndFetchProductList(imgRequestVO).block();
        String ingredients = getIngredientsOrThrow(ocrNlpResult);

        Optional<String> favoriteProteinCode = findFavoriteProteinCode(ingredients);
        PetVO petVO = PetVO.builder()
                           .id(petId)
                           .feedMainImgUrl(feedMainImgUrl)
                           .feedDescImgUrl(feedDescrImgUrl)
                           .favoriteFoodIngredients(ingredients)
                           .favoriteProteinCode(favoriteProteinCode.orElse(null))
                           .build();
        petMapper.updateFeed(petVO);
        return ocrNlpResult;
    }

    private String getIngredientsOrThrow(OcrNlpResultVO ocrNlpResult) {
        if (ocrNlpResult == null) {
            throw new BusinessException(INVALID_IMAGE_TO_OCR);
        }
        return ocrNlpResult.getIngredients();
    }

    @Override
    public List<PetVO> myPet(String memberId) {
        entityValidator.getPresentMember(memberId);
        List<PetVO> result = petMapper.myPet(memberId);
        return result;
    }

    @Override
    public List<PetVO> findPetsWithAllergies(String memberId) {
        List<PetVO> pets = petMapper.findPetsWithAllergiesByMemberId(memberId);
        log.info(pets);
        return pets;
    }

    @Override
    public PetVO updateMbti(String petId, PetVO petVO) {
        petVO.setId(petId);
        if (petMapper.updateMbtiById(petVO) < 1) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return petVO;
    }

    @Override
    @ServiceTimeTrace
    public OcrNlpResultVO getSuggestions(String petId) {
        PetVO pet = petMapper.findPetWithAllergiesById(petId)
                             .orElseThrow(() -> new BusinessException(ErrorCode.PET_NOT_FOUND));

        ImgRequestVO imgRequestVO = new ImgRequestVO(pet.getFeedDescImgUrl());
        OcrNlpResultVO ocrNlpResult = ocrNlpService.analyzeImageAndFetchProductList(imgRequestVO).block();
        Optional<String> favoriteProteinCode = null;
        if (ocrNlpResult != null) {
            favoriteProteinCode = findFavoriteProteinCode(ocrNlpResult.getIngredients());
        }

        PetVO petVO = PetVO.builder()
                           .id(petId)
                           .feedMainImgUrl(pet.getFeedMainImgUrl())
                           .feedDescImgUrl(pet.getFeedDescImgUrl())
                           .favoriteProteinCode(favoriteProteinCode.orElse(null))
                           .build();
        petMapper.updateFeed(petVO);

        return ocrNlpResult;
    }

    private Optional<String> findFavoriteProteinCode(String rawIngredients) {
        List<String> ingredients = List.of(rawIngredients.split(","));
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
