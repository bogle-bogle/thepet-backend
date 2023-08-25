package com.thehyundai.thepet.heendycar;

import java.util.List;

public interface HcService {

    HcBranchVO showBranchInfo(String branchCode);

    List<HcBranchVO> showAllBranches();

    HcReservationVO createReservation(HcReservationVO requestVO);

}
