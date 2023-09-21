package com.thehyundai.thepet.domain.subscription;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import static com.thehyundai.thepet.global.exception.ErrorCode.CURATION_SUBSCRIPTION_ALREADY_EXISTS;
import static com.thehyundai.thepet.global.exception.ErrorCode.PRODUCT_SUBSCRIPTION_ALREADY_EXISTS;

@Log4j2
@Service
@RequiredArgsConstructor
//@TimeTraceService
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subsMapper;

    @Override
    public SubscriptionVO createCurationSubscription(SubscriptionVO requestVO) {
        validatePresentCurationSubscription(requestVO);
        if (subsMapper.saveCurationSubscription(requestVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return requestVO;
    }

    @Override
    public SubscriptionVO createProductSubscription(SubscriptionVO requestVO) {
        validatePresentProductSubscription(requestVO);
        if (subsMapper.saveProductSubscription(requestVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return requestVO;
    }

    private void validatePresentCurationSubscription(SubscriptionVO requestVO) {
        subsMapper.findCurationSubscriptionByMemberId(requestVO)
                  .ifPresent(subscription -> {
                      throw new BusinessException(CURATION_SUBSCRIPTION_ALREADY_EXISTS);
                  });
    }

    private void validatePresentProductSubscription(SubscriptionVO requestVO) {
        subsMapper.findProductSubscriptionByMemberId(requestVO)
                  .ifPresent(subscription -> {
                      throw new BusinessException(PRODUCT_SUBSCRIPTION_ALREADY_EXISTS);
                  });
    }
}
