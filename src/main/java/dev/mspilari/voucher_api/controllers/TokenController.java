package dev.mspilari.voucher_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import dev.mspilari.voucher_api.dto.TokenDto;
import dev.mspilari.voucher_api.services.TokenService;

@Controller
public class TokenController {

    private TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @GetMapping("/vouchers/create")
    public String createToken(Model model) {

        tokenService.createToken(model);

        return "createToken";
    }

    @PostMapping("/vouchers/validate")
    public String validateTokens(@ModelAttribute TokenDto tokens, Model model) {

        tokenService.verifyTokens(model, tokens);

        return "validateTokens";
    }

}
