package com.thehyundai.thepet.heendycar;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class HeendyCarServiceImpl implements HeendyCarService {
    private final HeendyCarMapper heendyCarMapper;

    @Override
    public List<BranchHeendyCarVO> showAllBranches() {
        List<BranchHeendyCarVO> result = heendyCarMapper.findAllBranches();
        return result;
    }

    @Override
    public HeendyCarReservationVO createReservation(HeendyCarReservationVO requestVO) {
        return null;
    }
}
