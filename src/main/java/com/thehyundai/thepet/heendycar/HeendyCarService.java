package com.thehyundai.thepet.heendycar;

import java.util.List;

public interface HeendyCarService {

    List<BranchHeendyCarVO> showAllBranches();

    HeendyCarReservationVO createReservation(HeendyCarReservationVO requestVO);
}
