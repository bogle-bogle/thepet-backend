package com.thehyundai.thepet.domain.heendycar;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface HcBranchMapper {
    Optional<HcBranchVO> findBranchInfoByBranchCode(String branchCode);
    List<HcBranchVO> findAllBranches();
}
