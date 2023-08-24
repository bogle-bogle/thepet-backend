package com.thehyundai.thepet.subscription;

import com.thehyundai.thepet.global.DataValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subsMapper;
    private final DataValidator dataValidator;

    @Override
    public SubscriptionVO subscribeCuration(SubscriptionVO requestVO) {
        dataValidator.checkPresentCuration(requestVO.getCurationId());
        dataValidator.checkPresentMember(requestVO.getMemberId());

        requestVO.setCurationYn("Y");
        subsMapper.saveCurationSubscription(requestVO);
        return requestVO;
    }

    @Override
    public SubscriptionVO subscribeProduct(SubscriptionVO requestVO) {
        dataValidator.checkPresentProduct(requestVO.getProductId());
        dataValidator.checkPresentMember(requestVO.getMemberId());

        requestVO.setCurationYn("N");
        subsMapper.saveProductSubscription(requestVO);
        return requestVO;
    }

}
