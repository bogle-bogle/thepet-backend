package com.thehyundai.thepet.aws_s3;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class S3Service {
    private final AmazonS3 amazonS3;

    public String uploadFileAndGetUrl(MultipartFile file, String bucketName) throws IOException {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());

        try {
            amazonS3.putObject(bucketName, fileName, file.getInputStream(), metadata);
            String imageUrl = amazonS3.getUrl(bucketName, fileName).toString();
            return imageUrl;
        } catch (Exception e) {
            log.error("이미지 업로드 실패", e);
            throw new RuntimeException("이미지 업로드에 실패하였습니다.");
        }
    }
}
