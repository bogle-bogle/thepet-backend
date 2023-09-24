package com.thehyundai.thepet.domain.backoffice;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BackofficeMapper {

    void callMonthlySalesVolumeProcedure();

    List<BackOfficeVO> getTop10ProductsOfPreviousMonth();

}
