package com.thehyundai.thepet.subscription;

import java.util.List;

public interface SubsService {
     SubscriptionVO createSubscription(SubscriptionVO requestVO);

     CurationVO showCurationOfCurrMonth();

     List<CurationVO> showCurationOfLastOneYear();

     CurationVO showCurationDetail(Integer curationId);
}
