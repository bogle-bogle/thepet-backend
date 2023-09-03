package com.thehyundai.thepet.domain.recommendation;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Recommendation Controller", description = "추천 관련 컨트롤러")
@RequestMapping("/api/recommendation")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping
    @Operation(summary = "반려동물 맞춤 추천 상품 조회하기", description = "반려견 일반 정보 / 알러지 정보 / mbti 등으로 상품을 추천합니다.")
    public ResponseEntity<?> recommendProducts(@RequestParam Integer petId) {
        List<RecommendationVO> result = recommendationService.recommendProducts(petId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
