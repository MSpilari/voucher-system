package dev.mspilari.voucher_api.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import dev.mspilari.voucher_api.repositories.TokenRedisRepository;

@ExtendWith(MockitoExtension.class)
public class TokenServiceTest {
    @Mock
    private TokenRedisRepository tokenRedisRepository;

    @InjectMocks
    private TokenService tokenService;

    @Nested
    class GenerateToken {
        @Test
        void shouldCreateAValidToken() {
            // Arrange
            ReflectionTestUtils.setField(tokenService, "timeExpirationInSeconds", "60");
            doNothing().when(tokenRedisRepository).saveToken(anyString(), anyString(), anyLong());

            // Act
            Map<String, String> response = tokenService.generateAndSaveToken();

            // Assert
            assertNotNull(response);
            assertTrue(response.containsKey("token"));
            assertTrue(response.containsKey("validade"));

            String token = response.get("token");
            assertNotNull(token);
            assertFalse(token.isBlank());

            String validity = response.get("validade");
            assertNotNull(validity);
            assertFalse(validity.isBlank());

        }

        @Test
        void shouldThrowExceptionWhenRepositoryFails() {
            // Arrange
            ReflectionTestUtils.setField(tokenService, "timeExpirationInSeconds", "60");

            doThrow(new RuntimeException("Redis failure")).when(tokenRedisRepository).saveToken(anyString(),
                    anyString(), anyLong());

            // Act & Assert
            assertThrows(RuntimeException.class, () -> tokenService.generateAndSaveToken());

            // Verifica se o método foi chamado antes de falhar
            verify(tokenRedisRepository).saveToken(anyString(), anyString(), anyLong());
        }

    }
}
