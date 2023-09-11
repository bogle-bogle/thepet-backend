package com.thehyundai.thepet.redis;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;


@Service
public class RedisService {
    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public RedisService(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public String getCachedData(String key) {
        // Redis에서 캐시된 데이터를 가져옴
        String cachedData = redisTemplate.opsForValue().get(key);

        return cachedData;
    }

    public void cacheData(String key, String data, long timeoutInSeconds) {
        // 데이터를 Redis에 캐시하고 TTL(Time to Live) 설정
        redisTemplate.opsForValue().set(key, data, timeoutInSeconds, TimeUnit.SECONDS);
    }
}