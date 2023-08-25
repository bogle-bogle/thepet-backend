package com.thehyundai.thepet.heendycar;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HeendyCarMapper {
    List<BranchHeendyCarVO> findAllBranches();
}
