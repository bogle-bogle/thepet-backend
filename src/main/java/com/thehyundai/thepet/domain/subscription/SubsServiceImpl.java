package com.thehyundai.thepet.domain.subscription;

import com.thehyundai.thepet.global.TableStatus;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.domain.product.ProductService;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subsMapper;
    private final CurationMapper curationMapper;
    private final ProductService productService;

    @Override
    @TimeTraceService
    public SubscriptionVO createSubscription(SubscriptionVO requestVO) {
        requestVO.setCurationYn((requestVO.getCurationId() != null) ? TableStatus.Y.getValue() : TableStatus.N.getValue());
        if (subsMapper.saveCurationSubscription(requestVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return requestVO;
    }

    @Override
    @TimeTraceService
    public CurationVO showCurationOfCurrMonth() {
        LocalDate targetDate = LocalDate.now().withDayOfMonth(1);
        CurationVO curation = curationMapper.findCurationByPaymentDate(targetDate)
                                            .map(this::bindAllProductsInCuration)
                                            .orElseThrow(() -> new BusinessException(ErrorCode.CURATION_NOT_FOUND));
        return curation;
    }

    @Override
    @TimeTraceService
    public List<CurationVO> showCurationOfLastOneYear() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        List<CurationVO> curations = curationMapper.findCurationByStartingMonth(oneYearAgo)
                                                   .stream()
                                                   .map(this::bindAllProductsInCuration)
                                                   .collect(Collectors.toList());
        return curations;
    }

    @Override
    @TimeTraceService
    public CurationVO showCurationDetail(String curationId) {
        CurationVO curation = curationMapper.findCurationById(curationId)
                                            .map(this::bindAllProductsInCuration)
                                            .orElseThrow(() -> new BusinessException(ErrorCode.CURATION_NOT_FOUND));
        return curation;
    }
    @TimeTraceService
    private CurationVO bindAllProductsInCuration(CurationVO curation) {
        List<ProductVO> products = Stream.of(curation.getProduct1Id(), curation.getProduct2Id(), curation.getProduct3Id())
                                         .filter(Objects::nonNull)
                                         .map(productService::getProductDetail)
                                         .collect(Collectors.toList());
        curation.setProducts(products);
        return curation;
    }

}
