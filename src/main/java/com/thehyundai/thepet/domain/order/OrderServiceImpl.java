package com.thehyundai.thepet.domain.order;

import com.thehyundai.thepet.domain.cart.CartService;
import com.thehyundai.thepet.domain.cart.CartVO;
import com.thehyundai.thepet.domain.subscription.*;
import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import com.thehyundai.thepet.global.util.EntityValidator;
import com.thehyundai.thepet.global.cmcode.TableStatus;
import com.thehyundai.thepet.domain.product.ProductService;
import com.thehyundai.thepet.domain.product.ProductVO;
import com.thehyundai.thepet.global.jwt.AuthTokensGenerator;
import com.thehyundai.thepet.global.timetrace.TimeTraceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
@TimeTraceService
public class OrderServiceImpl implements OrderService {
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    private final AuthTokensGenerator authTokensGenerator;
    private final EntityValidator entityValidator;

    private final SubsService subsService;
    private final CartService cartService;
    private final ProductService productService;
    private final CurationService curationService;

    @Override
    @Transactional
    public OrderVO orderSelectedCart(String token, String tossOrderId, List<CartVO> selectedItems) {
        // 0. 유효성 검사 및 유저 검증
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        // 1. ORDER 테이블에 저장
        OrderVO order = buildSelectedCartOrder(memberId, selectedItems, tossOrderId);
        if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. ORDER_DETAIL 테이블에 저장
        List<OrderDetailVO> orderDetails = new ArrayList<>();
        for (CartVO cart : selectedItems) {
            OrderDetailVO orderDetail = buildCartOrderDetail(order.getId(), cart);
            if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
            orderDetails.add(orderDetail);
        }

        // 4. 반환값 생성
        order.setOrderDetails(orderDetails);
        return order;
    }

    @Override
    @Transactional
    public OrderVO orderWholeCart(String token, String tossOrderId) {
        // 0. 유효성 검사 및 유저 검증
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        // 1. 회원의 카트 전체 조회하기
        List<CartVO> wholeCart = cartService.getCart(memberId);

        // 2. ORDER 테이블에 저장
        OrderVO order = buildWholeCartOrder(memberId, wholeCart, tossOrderId);
        if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. ORDER_DETAIL 테이블에 저장
        List<OrderDetailVO> orderDetails = new ArrayList<>();
        for (CartVO cart : wholeCart) {     // 나중에 배치 INSERT로 성능 개선
            OrderDetailVO orderDetail = buildCartOrderDetail(order.getId(), cart);
            if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);
            orderDetails.add(orderDetail);
        }

        // 4. 반환값 생성
        order.setOrderDetails(orderDetails);
        return order;

    }



    @Override
    @Transactional
    public OrderVO createSubscriptionOrder(String token, SubscriptionVO requestVO) {
        // 0. 유효성 검사 및 필요한 데이터 불러오기
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);
        requestVO.setMemberId(memberId);

        // 1. ORDER 테이블에 저장
        CurationVO thisMonthCuration = curationService.showCurationOfCurrMonth();
        OrderVO order = buildCurationOrder(memberId, thisMonthCuration);
        if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 2. ORDER_DETAIL 테이블에 저장
        OrderDetailVO orderDetail = buildCurationOrderDetail(order.getId(), thisMonthCuration);
        if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. SUBSCRIPTION 테이블에 구독 정보 저장
        requestVO.setCurationYn(TableStatus.Y.getValue());
        subsService.createCurationSubscription(requestVO);

        // 4. 주문 내역 반환
        order.setOrderDetails(List.of(orderDetail));
        return order;
    }

    @Override
    @Transactional
    public OrderVO createRegularDeliveryOrder(String token, SubscriptionVO requestVO) {
        // 0. 유효성 검사 및 필요한 데이터 불러오기
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);
        requestVO.setMemberId(memberId);
        ProductVO product = productService.getProductDetail(requestVO.getProductId());

        // 1. ORDER 테이블에 저장
        OrderVO order = buildProductOrder(memberId, product);
        if (orderMapper.saveOrder(order) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 2. ORDER_DETAIL 테이블에 저장
        OrderDetailVO orderDetail = buildProductOrderDetail(order.getId(), product);
        if (orderDetailMapper.saveOrderDetail(orderDetail) == 0) throw new BusinessException(ErrorCode.DB_QUERY_EXECUTION_ERROR);

        // 3. SUBSCRIPTION 테이블에 구독 정보 저장
        requestVO.setCurationYn(TableStatus.N.getValue());
        subsService.createProductSubscription(requestVO);

        // 4. 주문 내역 반환
        order.setOrderDetails(List.of(orderDetail));
        return order;
    }

    @Override
    public OrderVO showOrderWithDetails(String orderId) {
        OrderVO result = orderMapper.getOrderWithOrderDetailsById(orderId)
                                    .orElseThrow(() -> new BusinessException(ErrorCode.ORDER_NOT_FOUND));
        return result;
    }



    @Override
    public List<OrderVO> showAllMyOrdersWithDetails(String token) {
        // 0. 유효성 검사 및 유저 검증
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        // 1. 데이터 조회하기
        List<OrderVO> result = orderMapper.showAllMyOrdersWithDetails(memberId);
        return result;
    }

    @Override
    public List<OrderVO> showMyNormalOrdersWithDetails(String token) {
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        List<OrderVO> result = orderMapper.showMyNormalOrdersWithDetails(memberId);
        return result;    }

    @Override
    public Map<String, List<OrderVO>> showMySubscriptionWithDetails(String token) {
        String memberId = authTokensGenerator.extractMemberId(token);
        entityValidator.getPresentMember(memberId);

        Map<String, List<OrderVO>> result = orderMapper.showMySubscriptionWithDetails(memberId)
                                                       .stream()
                                                       .collect(Collectors.groupingBy(order -> "Y".equals(order.getCurationYn()) ? "curationY" : "curationN"));
        return result;
    }

    private OrderVO buildCurationOrder(String memberId, CurationVO curation) {
        return OrderVO.builder()
                      .totalCnt(1)
                      .totalPrice(curation.getPrice())
                      .createdAt(LocalDate.now())
                      .memberId(memberId)
                      .subscribeYn(TableStatus.Y.getValue())
                      .curationYn(TableStatus.Y.getValue())
                      .build();
    }

    private OrderDetailVO buildCurationOrderDetail(String orderId, CurationVO curation) {
        return OrderDetailVO.builder()
                            .orderId(orderId)
                            .cnt(1)
                            .curationId(curation.getId())
                            .curationName(curation.getName())
                            .curationImgUrl(curation.getThumbnailImgUrl())
                            .curationPrice(curation.getPrice())
                            .build();
    }

    private OrderVO buildSelectedCartOrder(String memberId, List<CartVO> selectedCart, String tossOrderId) {
        return OrderVO.builder()
                .totalCnt(selectedCart.size())
                .totalPrice(calculateTotalPrice(selectedCart))
                .createdAt(LocalDate.now())
                .memberId(memberId)
                .subscribeYn(TableStatus.N.getValue())
                .tossOrderId(tossOrderId)
                .build();
    }
    private OrderVO buildWholeCartOrder(String memberId, List<CartVO> wholeCart, String tossOrderId) {
        return OrderVO.builder()
                .totalCnt(wholeCart.size())
                .totalPrice(calculateTotalPrice(wholeCart))
                .createdAt(LocalDate.now())
                .memberId(memberId)
                .subscribeYn(TableStatus.N.getValue())
                .tossOrderId(tossOrderId)
                .build();
    }

    private OrderDetailVO buildCartOrderDetail(String orderId, CartVO cart) {
        ProductVO product = productService.getProductDetail(cart.getProductId());
        return OrderDetailVO.builder()
                            .orderId(orderId)
                            .cnt(cart.getCnt())
                            .productId(cart.getProductId())
                            .productName(product.getName())
                            .productImgUrl(product.getMainImgUrl())
                            .productPrice(product.getPrice())
                            .build();
    }

    private OrderVO buildProductOrder(String memberId, ProductVO product) {
        return OrderVO.builder()
                      .totalCnt(1)
                      .totalPrice(product.getPrice())
                      .createdAt(LocalDate.now())
                      .memberId(memberId)
                      .subscribeYn(TableStatus.Y.getValue())
                      .build();
    }

    private OrderDetailVO buildProductOrderDetail(String orderId, ProductVO product) {
        return OrderDetailVO.builder()
                            .orderId(orderId)
                            .cnt(1)
                            .productId(product.getId())
                            .productName(product.getName())
                            .productImgUrl(product.getMainImgUrl())
                            .productPrice(product.getPrice())
                            .build();
    }

    private Integer calculateTotalPrice(List<CartVO> carts) {
        return  carts.stream()
                     .mapToInt(this::calculateEachCartPrice)
                     .sum();
    }

    private Integer calculateEachCartPrice(CartVO cart) {
        return cart.getCnt() + productService.getProductDetail(cart.getProductId()).getPrice();
    }

}
