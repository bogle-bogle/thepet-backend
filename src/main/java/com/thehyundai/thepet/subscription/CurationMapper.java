package com.thehyundai.thepet.subscription;

import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Mapper
public interface CurationMapper {
    Optional<CurationVO> findCurationById(Integer id);

    Optional<CurationVO> findCurationByPaymentDate(LocalDate paymentDate);

    List<CurationVO> findCurationByStartingMonth(LocalDate paymentDate);
}
