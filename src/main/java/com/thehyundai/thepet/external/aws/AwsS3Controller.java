package com.thehyundai.thepet.external.aws;

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

@Log4j2
@RestController
//@TimeTraceController
@Tag(name = "S3 Upload Controller", description = "S3 이미지 업로드 컨트롤러")
@RequestMapping("/api/upload")
@RequiredArgsConstructor
public class AwsS3Controller {
    private final AwsS3Service awsS3Service;

    @PostMapping
    // @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String imageUrl = awsS3Service.uploadToPetProfileBucket(file);
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }

    @PostMapping("/admin")
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> uploadAdminFile(@RequestParam("file") MultipartFile file) {
        String imageUrl = awsS3Service.uploadToAdminAssetBucket(file);
        return new ResponseEntity<>(imageUrl, HttpStatus.OK);
    }
}
