package com.thehyundai.thepet.subscription;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subsMapper;

    @Override
    public SubscriptionVO subscribeCuration(SubscriptionVO requestVO) {
        if (requestVO.getCurationId() == null) throw new BusinessException(ErrorCode.CURATION_NOT_FOUND);
        subsMapper.saveCurationSubscription(requestVO);
        return requestVO;
    }
}
