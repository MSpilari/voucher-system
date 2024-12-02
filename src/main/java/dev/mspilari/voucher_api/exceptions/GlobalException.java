package dev.mspilari.voucher_api.exceptions;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleValidationErrors(MethodArgumentNotValidException ex, Model model) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map((FieldError field) -> field.getField() + ": " + field.getDefaultMessage())
                .toList();

        model.addAttribute("erros", "Erros de validação: " + String.join(", ", errors));

        return "validateTokens";
    }
}
