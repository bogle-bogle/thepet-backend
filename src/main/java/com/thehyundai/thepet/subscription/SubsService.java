package com.thehyundai.thepet.subscription;

public interface SubsService {
     CurationVO showCurationOfCurrMonth();

     SubscriptionVO createSubscription(SubscriptionVO requestVO);
}
