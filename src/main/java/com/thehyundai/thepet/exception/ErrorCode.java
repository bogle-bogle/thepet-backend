package com.thehyundai.thepet.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /* Business Exceptions */
    // 400 BAD REQUEST
    INVALID_INPUT_VALUE("INVALID_INPUT_VALUE", "유효하지 않은 입력값입니다.", 400),
    TYPE_MISMATCH("TYPE_MISMATCH", "입력된 enum값이 유효하지 않습니다.", 400),
    METHOD_NOT_ALLOWED("METHOD_NOT_ALLOWED", "유효하지 않은 HTTP method입니다.", 400),

    // 403 FORBIDDEN

    // 404 NOT FOUND
    MEMBER_NOT_FOUND("MEMBER_NOT_FOUND", "존재하지 않는 사용자 ID입니다.", 404),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND", "존재하지 않는 상품 ID입니다.", 404),
    REVIEW_NOT_FOUND("REVIEW_NOT_FOUND", "존재하지 않는 댓글 ID입니다.", 404),
    URL_NOT_FOUND("URL_NOT_FOUND", "URL을 찾을 수 없습니다.", 404),
    CURATION_NOT_FOUND("CURATION_NOT_FOUND", "존재하지 않는 더펫박스 ID입니다.", 404),

    // 406 Not Acceptable


    // 500 INTERNAL SERVER ERROR
    DB_QUERY_EXECUTION_ERROR("DB_QUERY_EXECUTION_ERROR", "쿼리가 정상적으로 완료되지 않았습니다.", 500),

    /* Spring Basic Exceptions */
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "서버 오류가 발생했습니다.", 500);

    private final String code;
    private final String message;
    private final int status;

}