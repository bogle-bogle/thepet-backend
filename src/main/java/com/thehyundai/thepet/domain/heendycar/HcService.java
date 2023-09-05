package com.thehyundai.thepet.domain.heendycar;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface HcService {

    HcBranchVO showBranchInfo(String branchCode);

    List<HcBranchVO> showAllBranches();

    HcReservationVO createReservation(String token, HcReservationVO requestVO);


}
