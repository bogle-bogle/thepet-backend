package com.thehyundai.thepet.domain.mypet.customcard;

import com.thehyundai.thepet.global.timetrace.TimeTraceController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.thehyundai.thepet.global.util.Constant.HEADER_FILE_PARAM;
import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;

@Log4j2
@RestController
@TimeTraceController
@RequestMapping("/api/custom-card")
@RequiredArgsConstructor
@Tag(name = "Custom Card Controller", description = "현대백화점 카드 커스텀 관련 컨트롤러")
public class CustomCardController {
    private final CustomCardService customCardService;

    @PostMapping
    @Operation(summary = "내가 만든 커스텀 카드 저장하기", description = "내가 디자인한 커스텀 카드 정보를 저장합니다.")
    public ResponseEntity<?> saveCustomCardDesign(@RequestHeader(HEADER_TOKEN_PARAM) String token, @RequestParam(HEADER_FILE_PARAM) MultipartFile file) {
        CustomCardVO result = customCardService.saveCustomCardDesign(token, file);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
