package com.thehyundai.thepet.heendycar;

import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface HcReservationMapper {

    Integer saveReservation(HcReservationVO requestVO);
    Optional<HcReservationVO> findReservationById(Integer reservationId);
    Integer updateReservation(HcReservationVO reservation);
}
