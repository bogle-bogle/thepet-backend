package com.thehyundai.thepet.external.aws;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.thehyundai.thepet.global.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.UUID;

import static com.thehyundai.thepet.global.exception.ErrorCode.AWS_S3_UPLOAD_ERROR;
import static com.thehyundai.thepet.global.exception.ErrorCode.INVALID_IMAGE_FILE_TYPE;

@Log4j2
@Service
@RequiredArgsConstructor
public class AwsS3Service {
    private final AmazonS3 amazonS3;
    private final String S3_PET_FEED_BUCKET = "heendy-feed";
    private final String S3_PET_PROFILE_IMG_BUCKET = "heendy-pet";
    private final String S3_CUSTOM_CARD_BUCKET = "heendy-custom-cards";
    private final String S3_ADMIN_IMG_SRC_BUCKET = "heendy-assets";

    public String uploadToPetProfileBucket(MultipartFile file) {
        validateImgFileType(file);
        return uploadToS3Bucket(file, S3_PET_PROFILE_IMG_BUCKET);
    }

    public String uploadToAdminAssetBucket(MultipartFile file) {
        validateImgFileType(file);
        return uploadToS3Bucket(file, S3_ADMIN_IMG_SRC_BUCKET);
    }

    public String uploadToFeedBucket(MultipartFile file) {
        validateImgFileType(file);
        return uploadToS3Bucket(file, S3_PET_FEED_BUCKET);
    }

    public String uploadToCustomCardBucket(MultipartFile file) {
        validateImgFileType(file);
        return uploadToS3Bucket(file, S3_CUSTOM_CARD_BUCKET);
    }

    private void validateImgFileType(MultipartFile file) {
        if (!Objects.requireNonNull(file.getContentType()).startsWith("image/")) {      // 파일의 MIME 유형을 확인
            throw new BusinessException(INVALID_IMAGE_FILE_TYPE);                       // image/jpeg, image/png, image/gif이 아니라면 예외 처리
        }
    }

    private String uploadToS3Bucket(MultipartFile file, String bucketName) {
        // 1. 파일명 생성 및 메타데이터 생성
        String fileName = UUID.randomUUID() + "-" + file.getOriginalFilename();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        // 2. S3에 업로드
        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            return amazonS3.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            throw new BusinessException(AWS_S3_UPLOAD_ERROR);
        }
    }
}
