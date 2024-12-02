package dev.mspilari.voucher_api.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenDto(
        @NotBlank String token1,
        @NotBlank String token2,
        @NotBlank String token3,
        @NotBlank String token4,
        @NotBlank String token5) {

}
