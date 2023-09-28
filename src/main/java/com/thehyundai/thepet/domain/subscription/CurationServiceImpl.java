package com.thehyundai.thepet.domain.subscription;

import com.thehyundai.thepet.domain.product.ProductService;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
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
public class CurationServiceImpl implements CurationService {
    private final ProductService productService;
    private final CurationMapper curationMapper;

    @Override
    public CurationVO createCuration(CurationVO curationVO) {
        if (curationMapper.saveCuration(curationVO) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
        return curationVO;
    }

    @Override
    public CurationVO showCurationOfCurrMonth() {
        LocalDate targetDate = LocalDate.now().withDayOfMonth(1);
        CurationVO curation = curationMapper.findCurationByPaymentDate(targetDate)
                .map(this::bindAllProductsInCuration)
                .orElseThrow(() -> new BusinessException(ErrorCode.CURATION_NOT_FOUND));
        return curation;
    }

    @Override
    public List<CurationVO> showCurationOfLastOneYear() {
        LocalDate oneYearAgo = LocalDate.now().minusYears(1);
        List<CurationVO> curations = curationMapper.findCurationByStartingMonth(oneYearAgo)
                .stream()
                .map(this::bindAllProductsInCuration)
                .collect(Collectors.toList());
        return curations;
    }

    @Override
    public CurationVO showCurationDetail(String curationId) {
        CurationVO curation = curationMapper.findCurationById(curationId)
                .map(this::bindAllProductsInCuration)
                .orElseThrow(() -> new BusinessException(ErrorCode.CURATION_NOT_FOUND));
        return curation;
    }

    private CurationVO bindAllProductsInCuration(CurationVO curation) {
        List<ProductVO> products = Stream.of(curation.getProduct1Id(), curation.getProduct2Id(), curation.getProduct3Id())
                                         .filter(Objects::nonNull)
                                         .map(productService::getProductDetail)
                                         .collect(Collectors.toList());
        curation.setProducts(products);
        return curation;
    }
}
