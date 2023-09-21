package com.thehyundai.thepet.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* Business Exceptions */
    // 400 BAD REQUEST
    INVALID_INPUT_VALUE("유효하지 않은 입력값입니다.", 400),
    TYPE_MISMATCH("입력된 enum값이 유효하지 않습니다.", 400),
    METHOD_NOT_ALLOWED("유효하지 않은 HTTP method입니다.", 400),
    MISSING_REQUEST_HEADER("HTTP 요청 헤더에 인증 값이 존재하지 않습니다.", 400),
    INVALID_IMAGE_FILE_TYPE("유효하지 않은 확장자입니다. 이미지 파일만 업로드 가능합니다.", 400),

    // 401 Unauthorized
    EXPIRED_TOKEN("만료된 토큰입니다. 재로그인이 필요합니다.", 401),

    // 403 FORBIDDEN


    // 404 NOT FOUND
    MEMBER_NOT_FOUND("존재하지 않는 사용자 ID입니다.", 404),
    PRODUCT_NOT_FOUND("존재하지 않는 상품 ID입니다.", 404),
    REVIEW_NOT_FOUND("존재하지 않는 댓글 ID입니다.", 404),
    ORDER_NOT_FOUND("존재하지 않는 주문 ID입니다.", 404),
    PET_NOT_FOUND("존재하지 않는 반려동물 ID입니다.", 404),
    CURATION_NOT_FOUND("존재하지 않는 더펫박스 ID입니다.", 404),
    CM_CODE_NOT_FOUND("존재하지 않는 공통코드값입니다.", 404),
    RESERVATION_NOT_FOUND("존재하지 않는 예약 ID입니다.", 404),
    URL_NOT_FOUND("URL을 찾을 수 없습니다.", 404),

    // 406 Not Acceptable
    NO_HEENDYCAR_AVAILABLE("잔여 흰디카가 없습니다.", 406),
    NOT_AVAILABLE_RESERVATION_TIME("흰디카 예약은 최대 픽업 30분 전에만 가능합니다.", 406),
    NOT_AVAILABLE_BRANCH("흰디카 예약 가능한 점포가 아닙니다.", 406),
    INVALID_BIRTHDATE("유효하지 않은 출생일입니다.", 406),
    NO_PHONE_NUMBER("휴대폰 번호가 입력되지 않았습니다.", 406),
    CURATION_SUBSCRIPTION_ALREADY_EXISTS("이미 더펫박스를 구독한 회원입니다.", 406),
    PRODUCT_SUBSCRIPTION_ALREADY_EXISTS("이미 해당 상품을 구독한 회원입니다.", 406),
    RESERVATION_ALREADY_EXISTS("이미 오늘의 예약이 존재합니다.", 406),
    INVALID_PHONE_NUMBER("유효하지 않은 휴대폰번호입니다.", 406),

    // 500 INTERNAL SERVER ERROR
    DB_QUERY_EXECUTION_ERROR("쿼리가 정상적으로 완료되지 않았습니다.", 500),
    SMS_ERROR("SMS 전송에 실패하였습니다.", 500),
    AWS_S3_UPLOAD_ERROR("AWS S3 이미지 업로드에 실패하였습니다.", 500),

    /* Spring Basic Exceptions */
    INTERNAL_SERVER_ERROR("서버 오류가 발생했습니다.", 500);

    private final String message;
    private final int status;

}