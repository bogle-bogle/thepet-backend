package com.thehyundai.thepet.global.jwt;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.Date;

import static com.thehyundai.thepet.global.util.Constant.JWT_PREFIX;

@Log4j2
@Component
@RequiredArgsConstructor
public class AuthTokensGenerator {
//    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30;            // 30분
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 1000 * 60 * 30 * 100;            // 300분 - 개발용
//    private static final long ACCESS_TOKEN_EXPIRE_TIME = 10000;
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 1000 * 60 * 60 * 24 * 7;  // 7일

    private final JwtTokenProvider jwtTokenProvider;

    public AuthTokens generate(String memberId) {
        long now = (new Date()).getTime();
        Date accessTokenExpiredAt = new Date(now + ACCESS_TOKEN_EXPIRE_TIME);
        Date refreshTokenExpiredAt = new Date(now + REFRESH_TOKEN_EXPIRE_TIME);

        String subject = memberId;
        String accessToken = jwtTokenProvider.generate(subject, accessTokenExpiredAt);
        String refreshToken = jwtTokenProvider.generate(subject, refreshTokenExpiredAt);

        return AuthTokens.of(accessToken, refreshToken, JWT_PREFIX, Long.valueOf(ACCESS_TOKEN_EXPIRE_TIME / 1000L));
    }

    public String extractMemberId(String accessToken) {
        if (accessToken.startsWith(JWT_PREFIX)) {
            accessToken = accessToken.substring(7);
        }
        return jwtTokenProvider.extractSubject(accessToken);
    }
}