package com.thehyundai.thepet.domain.subscription;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CurationServiceImpl implements CurationService {

    private final CurationMapper curationMapper;
    @Override
    @TimeTraceService
    public CurationVO createCuration(CurationVO curationVO) {
        if (curationMapper.saveCuration(curationVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return curationVO;
    }
}
