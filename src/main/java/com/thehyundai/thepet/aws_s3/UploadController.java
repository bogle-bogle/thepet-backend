package com.thehyundai.thepet.aws_s3;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Log4j2
@RestController
@Tag(name = "S3 Upload Controller", description = "S3 이미지 업로드 컨트롤러")
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class UploadController {
    private final S3Service s3Service;

    @PostMapping
    // @PreAuthorize("hasRole('ROLE_USER')") => 사용자 제한 시큐리티한다면..
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        try {
            // 파일의 MIME 유형을 확인하여 이미지 파일인지 확인 => image/jpeg, image/png, image/gif에 한함
            if (!file.getContentType().startsWith("image/")) {
                return new ResponseEntity<>("이미지 파일만 업로드 가능", HttpStatus.BAD_REQUEST);
            }
            String imageUrl = s3Service.uploadFileAndGetUrl(file);
            return new ResponseEntity<>(imageUrl, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("이미지 업로드 실패", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
