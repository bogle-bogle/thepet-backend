package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.external.ocrnlp.OcrNlpResultVO;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;

@Log4j2
@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
@Tag(name = "Pet Controller", description = "반려동물 관련 컨트롤러")
public class PetController {
    private final PetService petService;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping(value = "/feed/suggestion/{petId}")
    @Operation(summary = "반려동물의 기존 정보로 사료 추천받기", description = "반려동물이 가장 좋아하는 사료의 성분으로 유사도 분석 결과를 리턴합니다.")
    public ResponseEntity<?> getSuggestions(@PathVariable("petId") String petId) {
        OcrNlpResultVO result = petService.getSuggestions(petId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/feed/suggestion")
    @Operation(summary = "반려동물이 좋아하는 사료 정보 저장/추천받기", description = "반려동물이 가장 좋아하는 사료의 전 성분, 주 성분, 사료 표지 이미지, 사료 성분표 이미지를 저장하고, 유사도 분석 결과를 리턴합니다.")
    public ResponseEntity<?> updateFeed(@RequestParam("petId") String petId,
                                        @RequestParam(value = "feedMainImgFile", required = false) MultipartFile feedMainImgFile,
                                        @RequestParam(value = "feedDescImgFile", required = false) MultipartFile feedDescImgFile) {
        OcrNlpResultVO result = petService.updateFeed(new PetSuggestionRequestVO(petId, feedMainImgFile, feedDescImgFile));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "나의 반려동물 목록 조회하기", description = "나의 반려동물 정보를 모두 조회합니다.")
    public ResponseEntity<?> myPet(@RequestHeader(HEADER_TOKEN_PARAM) String token){
        String memberId = authTokensGenerator.extractMemberId(token);
        List<PetVO> myPets = petService.myPet(memberId);
        return new ResponseEntity<>(myPets, HttpStatus.OK);
    }

    @PutMapping("/mbti/{petId}")
    @Operation(summary = "반려동물 MBTI 저장하기", description = "반려동물의 MBTI를 저장합니다.")
    public ResponseEntity<?> updateMbti(@PathVariable String petId, @RequestBody PetVO petVO) {
        PetVO result = petService.updateMbti(petId, petVO);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
