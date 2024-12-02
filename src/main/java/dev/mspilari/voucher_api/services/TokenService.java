package dev.mspilari.voucher_api.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import dev.mspilari.voucher_api.dto.TokenDto;

@Service
public class TokenService {

    @Value("${expiration_time:60}")
    private String timeExpirationInSeconds;

    private RedisTemplate<String, String> redisTemplate;

    public TokenService(RedisTemplate<String, String> template) {
        this.redisTemplate = template;
    }

    public Map<String, String> generateAndSaveToken() {
        String token = generateUuidToken();

        Long timeExpiration = parseStringToLong(timeExpirationInSeconds);
        String validity = formatExpirationDate();

        var response = new HashMap<String, String>();

        redisTemplate.opsForValue().set(token, "Válido até: " + validity, timeExpiration, TimeUnit.SECONDS);

        response.put("token", token);
        response.put("validade", validity);

        return response;
    }

    private String generateUuidToken() {
        return UUID.randomUUID().toString();
    }

    private Long parseStringToLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid value for expiration time: " + value, e);
        }
    }

    private String formatExpirationDate() {
        Instant now = Instant.now();
        ZonedDateTime expirationDate = ZonedDateTime.ofInstant(
                now.plusSeconds(parseStringToLong(timeExpirationInSeconds)),
                ZoneId.systemDefault());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return expirationDate.format(formatter);
    }

    public Map<String, String> verifyTokens(TokenDto tokens) {

        var response = new HashMap<String, String>();
        List<String> tokensList = tokenDto2List(tokens);

        if (!areTokensUnique(tokens)) {
            response.put("erros", "Os tokens não podem ser iguais");
            return response;
        }

        if (tokensExist(tokensList)) {
            response.put("erros", "Tokens informados são inválidos.");
            return response;
        }

        redisTemplate.delete(tokensList);
        response.put("message", "Os tokens são válidos");
        return response;

    }

    private boolean areTokensUnique(TokenDto tokens) {
        List<String> tokensList = tokenDto2List(tokens);
        return new HashSet<>(tokensList).size() == tokensList.size();
    }

    private List<String> tokenDto2List(TokenDto tokens) {
        return List.of(tokens.token1(), tokens.token2(), tokens.token3(), tokens.token4(), tokens.token5());
    }

    private boolean tokensExist(List<String> tokensList) {
        return redisTemplate.opsForValue().multiGet(tokensList).contains(null);
    }
}
