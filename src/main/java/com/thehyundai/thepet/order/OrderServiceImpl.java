package com.thehyundai.thepet.order;

import com.thehyundai.thepet.exception.BusinessException;
import com.thehyundai.thepet.exception.ErrorCode;
import com.thehyundai.thepet.global.DataValidator;
import com.thehyundai.thepet.product.ProductMapper;
import com.thehyundai.thepet.product.ProductVO;
import com.thehyundai.thepet.subscription.CurationMapper;
import com.thehyundai.thepet.subscription.CurationVO;
import com.thehyundai.thepet.subscription.SubsService;
import com.thehyundai.thepet.subscription.SubscriptionVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static com.thehyundai.thepet.global.Constant.STATUS_Y;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    private final DataValidator dataValidator;
    private final SubsService subsService;
    private final CurationMapper curationMapper;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public OrderVO createSubscriptionOrder(SubscriptionVO requestVO) {
        // 0. 유효성 검사 및 필요한 데이터 불러오기
        dataValidator.checkPresentMember(requestVO.getMemberId());
        CurationVO curation = curationMapper.findCurationById(requestVO.getCurationId())
                                            .orElseThrow(() -> new BusinessException(ErrorCode.CURATION_NOT_FOUND));

        // 1. ORDER 테이블에 저장
        OrderVO order = buildCurationOrder(requestVO.getMemberId(), curation);
        if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 2. ORDER_DETAIL 테이블에 저장
        OrderDetailVO orderDetail = buildCurationOrderDetail(order.getId(), curation);
        if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. SUBSCRIPTION 테이블에 구독 정보 저장
        subsService.createSubscription(requestVO);

        // 4. 주문 내역 반환
        order.setOrderDetails(List.of(orderDetail));
        return order;
    }

    @Override
    public OrderVO createRegularDeliveryOrder(SubscriptionVO requestVO) {
        // 0. 유효성 검사 및 필요한 데이터 불러오기
        dataValidator.checkPresentMember(requestVO.getMemberId());
        ProductVO product = productMapper.findProductById(requestVO.getProductId())
                                           .orElseThrow(() -> new BusinessException(ErrorCode.PRODUCT_NOT_FOUND));

        // 1. ORDER 테이블에 저장
        OrderVO order = buildProductOrder(requestVO.getMemberId(), product);
        if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 2. ORDER_DETAIL 테이블에 저장
        OrderDetailVO orderDetail = buildProductOrderDetail(order.getId(), product);
        if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. SUBSCRIPTION 테이블에 구독 정보 저장
        subsService.createSubscription(requestVO);

        // 4. 주문 내역 반환
        order.setOrderDetails(List.of(orderDetail));
        return order;
    }

    @Override
    public OrderVO showOrderWithDetails(Integer orderId) {
        OrderVO result = orderMapper.getOrderWithOrderDetailsById(orderId);
        return result;
    }

    @Override
    public List<OrderVO> showAllMyOrdersWithDetails(Integer memberId) {
        List<OrderVO> result = orderMapper.showAllMyOrdersWithDetails(memberId);
        log.info(result);
        return result;
    }

    private OrderVO buildCurationOrder(Integer memberId, CurationVO curation) {
        return OrderVO.builder()
                      .totalCnt(1)
                      .totalPrice(curation.getPrice())
                      .createdAt(LocalDate.now())
                      .memberId(memberId)
                      .subscribeYn(STATUS_Y)
                      .build();
    }

    private OrderDetailVO buildCurationOrderDetail(Integer orderId, CurationVO curation) {
        return OrderDetailVO.builder()
                            .orderId(orderId)
                            .cnt(1)
                            .curationId(curation.getId())
                            .curationName(curation.getName())
                            .curationImgUrl(curation.getImgUrl())
                            .curationPrice(curation.getPrice())
                            .build();
    }

    private OrderVO buildProductOrder(Integer memberId, ProductVO product) {
        return OrderVO.builder()
                      .totalCnt(1)
                      .totalPrice(product.getPrice())
                      .createdAt(LocalDate.now())
                      .memberId(memberId)
                      .subscribeYn(STATUS_Y)
                      .build();
    }

    private OrderDetailVO buildProductOrderDetail(Integer orderId, ProductVO product) {
        return OrderDetailVO.builder()
                .orderId(orderId)
                .cnt(1)
                .productId(product.getId())
                .productName(product.getName())
                .productImgUrl(product.getMainImgUrl())
                .productPrice(product.getPrice())
                .build();
    }

}
