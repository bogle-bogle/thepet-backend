package com.thehyundai.thepet.domain.heendycar;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HcReservationMapper {

    Integer saveReservation(HcReservationVO requestVO);

    Optional<HcReservationVO> findReservationById(String reservationId);

    Integer updateReservation(HcReservationVO reservation);

    List<HcReservationVO> showAllMyReservations(String memberId);

}
