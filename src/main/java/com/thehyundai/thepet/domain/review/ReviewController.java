package com.thehyundai.thepet.domain.review;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @GetMapping("/list/{productId}")
    public ResponseEntity<List<ReviewVO>> getReviews(@PathVariable String productId) {
        return ResponseEntity.ok(reviewService.getAllReviews(productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewVO> getReviewDetail(@PathVariable String id) {
        return ResponseEntity.ok(reviewService.getReviewDetail(id));
    }
}
