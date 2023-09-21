package com.thehyundai.thepet.global.jwt;

import com.thehyundai.thepet.global.exception.BusinessException;
import com.thehyundai.thepet.global.exception.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt-error")
public class JwtErrorController {

    @RequestMapping(value = "/login-required", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public void controlLoginError(HttpServletRequest request) {
        throw new BusinessException(ErrorCode.LOGIN_REQUIRED);
    }

    @RequestMapping(value = "/member", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public void controlMemberError(HttpServletRequest request) {
        throw new BusinessException(ErrorCode.MEMBER_NOT_FOUND);
    }

    @RequestMapping(value = "/expired", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
    public void controlExpiredTokenError(HttpServletRequest request) {
        throw new BusinessException(ErrorCode.EXPIRED_TOKEN);
    }

}
