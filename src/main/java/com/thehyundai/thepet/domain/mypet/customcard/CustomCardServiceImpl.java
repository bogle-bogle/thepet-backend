package com.thehyundai.thepet.domain.mypet.customcard;

import com.thehyundai.thepet.external.aws.AwsS3Service;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomCardServiceImpl implements CustomCardService {
    private final CustomCardMapper customCardMapper;
    private final AwsS3Service awsS3Service;
    private final AuthTokensGenerator authTokensGenerator;

    @Override
    public CustomCardVO saveCustomCardDesign(String token, MultipartFile frontImgFile, MultipartFile backImgFile) {
        String memberId = authTokensGenerator.extractMemberId(token);
        String frontImgUrl = awsS3Service.uploadToCustomCardBucket(frontImgFile);
        String backImgUrl = awsS3Service.uploadToCustomCardBucket(backImgFile);
        CustomCardVO customCard = CustomCardVO.builder()
                                              .memberId(memberId)
                                              .frontImgUrl(frontImgUrl)
                                              .backImgUrl(backImgUrl)
                                              .createdAt(new Timestamp(System.currentTimeMillis()))
                                              .build();
        if (customCardMapper.saveCustomCardDesign(customCard) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return customCard;
    }

}
