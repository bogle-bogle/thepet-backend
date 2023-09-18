package com.thehyundai.thepet.domain.subscription;

import java.util.List;

public interface CurationService {

    CurationVO createCuration(CurationVO curationVO);

    CurationVO showCurationOfCurrMonth();

    List<CurationVO> showCurationOfLastOneYear();

    CurationVO showCurationDetail(String curationId);
}
