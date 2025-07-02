package dev.mspilari.voucher_api.repositories;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TokenRedisRepository {

    private RedisTemplate<String, String> redisTemplate;

    public TokenRedisRepository(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveToken(String token, String validity, long timeExpiration) {
        redisTemplate.opsForValue().set(token, "Válido até: " + validity, timeExpiration, TimeUnit.SECONDS);
    }

    public boolean allTokensAreValid(List<String> tokensList) {
        return redisTemplate.opsForValue().multiGet(tokensList).contains(null);
    }

    public void deleteAListOfTokens(List<String> keysList) {
        redisTemplate.delete(keysList);
    }

    public boolean isValid(String token) {
        String value = redisTemplate.opsForValue().get(token);
        return value != null && !value.isBlank();
    }

    public void deleteSingleToken(String token) {
        redisTemplate.delete(token);
    }
}
