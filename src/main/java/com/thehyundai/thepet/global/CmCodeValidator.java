package com.thehyundai.thepet.global;

import com.thehyundai.thepet.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.thehyundai.thepet.exception.ErrorCode.CM_CODE_NOT_FOUND;

@Component
@RequiredArgsConstructor
public class CmCodeValidator {
    private final CmCodeMapper cmCodeMapper;

    public void checkPresentCode(String codeValue) {
        cmCodeMapper.findCmCodeByCodeValue(codeValue)
                .orElseThrow(() -> new BusinessException(CM_CODE_NOT_FOUND));
    }

}
