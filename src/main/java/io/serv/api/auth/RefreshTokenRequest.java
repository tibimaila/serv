package io.serv.api.auth;

import jakarta.validation.constraints.NotBlank;
 
/**
 * Request payload used to obtain a new access token
 * using a valid refresh token.
 *
 * @param refreshToken refresh token
 */
public record RefreshTokenRequest(
        @NotBlank(message = "Please provide a refresh token.")
        String refreshToken
) {}

