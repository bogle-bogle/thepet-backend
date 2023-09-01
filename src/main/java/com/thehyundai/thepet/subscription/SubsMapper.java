package com.thehyundai.thepet.subscription;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubsMapper {

    Integer saveCurationSubscription(SubscriptionVO requestVO);

    Integer saveProductSubscription(SubscriptionVO requestVO);
}
