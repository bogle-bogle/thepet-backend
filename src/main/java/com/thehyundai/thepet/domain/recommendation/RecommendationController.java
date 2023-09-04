package com.thehyundai.thepet.domain.recommendation;

import com.thehyundai.thepet.domain.product.ProductVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "Recommendation Controller", description = "상품 추천 관련 컨트롤러")
@RequestMapping("/api/recommendation")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @GetMapping("/simple/{petId}")
    @Operation(summary = "반려동물 조건에 맞는 상품 추천", description = "나의 반려동물이 좋아하는 단백질원과, 반려동물 연령에 맞는 상품 추천하되, 주 재료에 알러지 성분이 있는 상품은 제거하고 보여줍니다.")
    public ResponseEntity<?> recommendProductsSimply(@PathVariable Integer petId) {
        RecommendationVO result = recommendationService.recommendProductsSimply(petId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/detail/{petId}")
    @Operation(summary = "나의 반려동물 종류 / 비슷한 연령대의 인기 상품 추천", description = "나의 반려동물과 같은 종, 같은 연령대의 반려동물들이 많이 구매한 제품 순으로 추천하되, 주 재료에 알러지 성분이 있는 상품은 제거하고 보여줍니다.")
    public ResponseEntity<?> recommendProductsInDetail(@PathVariable Integer petId) {
        RecommendationVO result = recommendationService.recommendProductsInDetail(petId);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/mbti")
    @Operation(summary = "MBTI 유형에 맞게 추천", description = "특정 MBTI 유형의 반려동물들이 많이 구매한 제품 순으로 추천하여 보여줍니다.")
    public ResponseEntity<?> recommendProductsInDetail(@RequestParam("type") String mbtiType) {
        List<ProductVO> result = recommendationService.recommendProductsByMbti(mbtiType);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
