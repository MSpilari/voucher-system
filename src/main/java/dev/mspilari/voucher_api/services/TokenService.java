package dev.mspilari.voucher_api.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dev.mspilari.voucher_api.dto.TokenDto;

@Service
public class TokenService {

    public void createToken(Model model) {
        String token = UUID.randomUUID().toString();

        model.addAttribute("token", token);
    }

    public void verifyTokens(Model model, TokenDto tokens) {
        model.addAttribute("message", "Ok");
    }
}
