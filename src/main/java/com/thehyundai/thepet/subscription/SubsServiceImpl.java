package com.thehyundai.thepet.subscription;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import com.thehyundai.thepet.product.ProductService;
import com.thehyundai.thepet.product.ProductVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.thehyundai.thepet.global.Constant.STATUS_N;
import static com.thehyundai.thepet.global.Constant.STATUS_Y;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubsServiceImpl implements SubsService {
    private final SubsMapper subsMapper;
    private final CurationMapper curationMapper;
    private final ProductService productService;

    @Override
    public SubscriptionVO createSubscription(SubscriptionVO requestVO) {
        requestVO.setCurationYn((requestVO.getCurationId() != null) ? STATUS_Y : STATUS_N);
        if (subsMapper.saveCurationSubscription(requestVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return requestVO;
    }

    @Override
    public CurationVO showCurationOfCurrMonth() {
        LocalDate targetDate = LocalDate.now().withDayOfMonth(1);
        CurationVO curation = curationMapper.findCurationByPaymentDate(targetDate)
                                            .orElseThrow(() -> new BusinessException(ErrorCode.CURATION_NOT_FOUND));

        List<ProductVO> products = bindAllProductsInCuration(curation);
        curation.setProducts(products);
        return curation;
    }

    @Override
    public List<CurationVO> showCurationOfLastOneYear() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);

        List<CurationVO> curations = curationMapper.findCurationByStartingMonth(oneYearAgo);
        for (CurationVO curation : curations) {
            curation.setProducts(bindAllProductsInCuration(curation));
        }
        return curations;
    }

    private List<ProductVO> bindAllProductsInCuration(CurationVO curation) {
        return Stream.of(curation.getProduct1Id(), curation.getProduct2Id(), curation.getProduct3Id())
                     .filter(Objects::nonNull)
                     .map(productService::getProductDetail)
                     .collect(Collectors.toList());
    }

}
