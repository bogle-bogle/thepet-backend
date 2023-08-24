package com.thehyundai.thepet.subscription;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.thehyundai.thepet.global.Constant.STATUS_N;
import static com.thehyundai.thepet.global.Constant.STATUS_Y;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subsMapper;

    @Override
    public SubscriptionVO createSubscription(SubscriptionVO requestVO) {
        requestVO.setCurationYn((requestVO.getCurationId() != null) ? STATUS_Y : STATUS_N);
        if (subsMapper.saveCurationSubscription(requestVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return requestVO;
    }

}
