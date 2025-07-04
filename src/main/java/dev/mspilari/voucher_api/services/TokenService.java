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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import dev.mspilari.voucher_api.dto.TokenDto;
import dev.mspilari.voucher_api.repositories.TokenRedisRepository;
import dev.mspilari.voucher_api.utils.NetworkUtils;
import dev.mspilari.voucher_api.utils.QRCodeGenerator;

@Service
public class TokenService {

    @Value("${expiration_time:60}")
    private String timeExpirationInSeconds;

    private TokenRedisRepository tokenRedisRepository;

    private QRCodeGenerator qrCodeGenerator;

    private NetworkUtils networkUtils;

    public TokenService(TokenRedisRepository tokenRedisRepository, QRCodeGenerator qrCodeGenerator,
            NetworkUtils networkUtils) {
        this.tokenRedisRepository = tokenRedisRepository;
        this.qrCodeGenerator = qrCodeGenerator;
        this.networkUtils = networkUtils;
    }

    public Map<String, String> generateAndSaveToken() {
        String token = generateUuidToken();

        Long timeExpiration = parseStringToLong(timeExpirationInSeconds);
        String validity = formatExpirationDate();

        var response = new HashMap<String, String>();

        String ip = networkUtils.getLocalIpAddress();
        String qrCodeContent = "http://" + ip + ":8080/voucher/validate?token=" + token;
        String qrCodeBase64 = qrCodeGenerator.generateQRCodeBase64(qrCodeContent, 200, 200);

        tokenRedisRepository.saveToken(token, validity, timeExpiration);

        response.put("token", token);
        response.put("validade", validity);
        response.put("qrCode", qrCodeBase64);

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

        if (tokenRedisRepository.allTokensAreValid(tokensList)) {
            response.put("erros", "Tokens informados são inválidos.");
            return response;
        }

        tokenRedisRepository.deleteAListOfTokens(tokensList);

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

    public Map<String, String> verifySingleToken(String token) {
        var response = new HashMap<String, String>();

        if (!tokenRedisRepository.isValid(token)) {
            response.put("message", "Token inválido/inexistente");
            return response;
        }

        tokenRedisRepository.deleteSingleToken(token);

        response.put("message", "Token deletado com sucesso !");

        return response;

    }
}
