package com.thehyundai.thepet.global.jwt;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.servlet.HandlerInterceptor;

import static com.thehyundai.thepet.global.util.Constant.HEADER_TOKEN_PARAM;

@Log4j2
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor  {
    private final AuthTokensGenerator authTokensGenerator;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(HEADER_TOKEN_PARAM);

        if (token == null || token.isEmpty()) {
            request.getRequestDispatcher("/api/jwt-error/login-required").forward(request, response);
            return false;
        }

        try {
            String memberId = authTokensGenerator.extractMemberId(token);
            if (memberId == null) {
                request.getRequestDispatcher("/api/jwt-error/member").forward(request, response);
                return false;
            }
        } catch (BusinessException e) {
            if (e.getErrorCode() == ErrorCode.EXPIRED_TOKEN) {
                request.getRequestDispatcher("/api/jwt-error/expired").forward(request, response);
                return false;
            }
           else {
               throw e;
            }
        }
        return true;
    }
}
