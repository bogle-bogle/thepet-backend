package com.thehyundai.thepet.domain.subscription;

import java.util.List;

public interface SubsService {
     SubscriptionVO createSubscription(SubscriptionVO requestVO);

     CurationVO showCurationOfCurrMonth();

     List<CurationVO> showCurationOfLastOneYear();

     CurationVO showCurationDetail(String curationId);
}
