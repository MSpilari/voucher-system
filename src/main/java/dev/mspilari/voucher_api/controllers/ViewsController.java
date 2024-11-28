package dev.mspilari.voucher_api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {

    @GetMapping("/")
    public String seeHomePage() {
        return "index";
    }

    @GetMapping("/vouchers/create")
    public String createTokenPage() {
        return "createToken";
    }

    @GetMapping("/vouchers/validate")
    public String verifyTokenPage() {
        return "validateTokens";
    }

}
