package com.thehyundai.thepet.domain.backoffice;

import java.util.List;


public interface BackofficeService {

    void callMonthlySalesVolumeProcedure();

    List<BackOfficeVO> getTop10Products();

}
