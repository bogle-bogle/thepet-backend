package com.thehyundai.thepet.domain.subscription;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface SubsMapper {

    Integer saveCurationSubscription(SubscriptionVO requestVO);

    Integer saveProductSubscription(SubscriptionVO requestVO);

    Optional<SubscriptionVO> findCurationSubscriptionByMemberId(SubscriptionVO requestVO);

    Optional<SubscriptionVO> findProductSubscriptionByMemberId(SubscriptionVO requestVO);

    Integer findSubsCntByMemberId(String memberId);
}
