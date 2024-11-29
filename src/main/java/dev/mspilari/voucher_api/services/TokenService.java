package dev.mspilari.voucher_api.services;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public void verifyTokens(Model model, TokenDto tokens) {
        model.addAttribute("message", "Ok");
    }
}
