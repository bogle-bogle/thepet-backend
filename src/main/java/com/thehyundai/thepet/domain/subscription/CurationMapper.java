package com.thehyundai.thepet.domain.subscription;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CurationMapper {

    Integer saveCuration(CurationVO curationVO);

    Optional<CurationVO> findCurationById(String id);

    Optional<CurationVO> findCurationByPaymentDate(LocalDate paymentDate);

    List<CurationVO> findCurationByStartingMonth(LocalDate paymentDate);
}
