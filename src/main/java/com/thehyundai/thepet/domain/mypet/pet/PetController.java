package com.thehyundai.thepet.domain.mypet.pet;

import com.thehyundai.thepet.global.cmcode.CmCodeVO;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.global.timetrace.TimeTrace;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/pet")
@RequiredArgsConstructor
@Tag(name = "Pet Controller", description = "반려동물 관련 컨트롤러")
public class PetController {
    private final PetService petService;
    private final AuthTokensGenerator authTokensGenerator;

    @PutMapping("/feed/{id}")
    @Operation(summary = "반려동물이 좋아하는 사료 정보 저장하기", description = "반려동물이 가장 좋아하는 사료의 전 성분, 주 성분, 사료 표지 이미지, 사료 성분표 이미지를 저장합니다.")
    public ResponseEntity<Integer> updateFeed(@PathVariable String id, @RequestBody PetVO petVO){
        return ResponseEntity.ok(petService.updateFeed(petVO,id));
    }

    @GetMapping
    @TimeTrace
    @Operation(summary = "나의 반려동물 목록 조회하기", description = "나의 반려동물 정보를 모두 조회합니다.")
    public ResponseEntity<List<PetVO>> myPet(@RequestHeader("Authorization") String token){
        Integer memberId = authTokensGenerator.extractMemberId(token);
        return ResponseEntity.ok(petService.myPet(memberId));
    }

    @GetMapping("/code")
    @Operation(summary = "공통 코드 조회하기", description = "공통 코드 테이블의 값들을 모두 불러옵니다.")
    public ResponseEntity<List<CmCodeVO>> getAllCode(){
        return ResponseEntity.ok(petService.getAllCode());
    }
}
