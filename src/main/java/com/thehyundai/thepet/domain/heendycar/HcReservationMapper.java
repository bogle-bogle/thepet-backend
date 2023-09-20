package com.thehyundai.thepet.domain.heendycar;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HcReservationMapper {

    List<HcReservationVO> findBranchReservation(String branchCode);

    Integer saveReservation(HcReservationVO requestVO);

    Optional<HcReservationVO> findReservationById(String reservationId);

    Integer updateReservation(HcReservationVO reservation);

    List<HcReservationVO> showAllMyReservations(String memberId);

    Integer cancelReservation(String reservationId);

    Optional<HcReservationVO> findPresentReservation(String memberId);

}
