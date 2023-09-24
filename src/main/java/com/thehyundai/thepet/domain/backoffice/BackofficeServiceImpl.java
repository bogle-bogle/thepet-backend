package com.thehyundai.thepet.domain.backoffice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BackofficeServiceImpl implements BackofficeService {

    @Autowired
    private BackofficeMapper backofficeMapper;

    @Override
    @Scheduled(cron = "0 0 1 1 * ?") // 매월 1일 새벽 1시에 실행
    public void callMonthlySalesVolumeProcedure() {
        backofficeMapper.callMonthlySalesVolumeProcedure();
    }

    @Override
    public List<BackOfficeVO> getTop10Products() {
        return backofficeMapper.getTop10Products();
    }
}
