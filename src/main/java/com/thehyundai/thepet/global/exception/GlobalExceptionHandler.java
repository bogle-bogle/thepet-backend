package com.thehyundai.thepet.global.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.net.BindException;

@Log4j2
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Business Exception Hanlding
     * 비즈니스 요구사항 예외 처리
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("[BUSINESS EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = new ErrorResponse(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }

    // ---------------------------------------------------------------------------------------------------------------

    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("[ARGUMENT NOT VALID EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     * @ModelAttribute 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("[BIND EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = ErrorCode.INVALID_INPUT_VALUE;
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("[ARGUMENT TYPE MISMATCH EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = ErrorCode.TYPE_MISMATCH;
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.getStatus()));

    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("[HTTP REQUEST METHOD NOT SUPPORTED EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = ErrorCode.METHOD_NOT_ALLOWED;
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.getStatus()));
    }

    /**
     * 500 서버 오류 발생
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleInteralServerException(Exception e) {
        log.error("[INTERNAL SERVER EXCEPTION EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = ErrorCode.INTERNAL_SERVER_ERROR;
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.getStatus()));
    }

    /** 잘못된 URL 요청시 - 404 Not Found
     * 500 메세지는 Internal Server Error이므로 @ExceptionHandler를 이용해서 처리가 가능하지만,
     * 404 메세지는 문법 오류가 아니고 잘못된 URL을 호출할 때 보이므로 다르게 처리해주어야 함.
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handle404(NoHandlerFoundException e){
        log.error("[NO HANDLER EXCEPTION HANDLING] ", e);
        final ErrorCode errorCode = ErrorCode.URL_NOT_FOUND;
        return new ResponseEntity<>(new ErrorResponse(errorCode), HttpStatus.valueOf(errorCode.getStatus()));
    }

}