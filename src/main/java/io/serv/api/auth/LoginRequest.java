package io.serv.api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;


/**
 * Request payload used to authenticate a user.
 *
 * @param email user's email address
 * @param password user's password
 */
public record LoginRequest(
        @NotBlank(message = "Please enter your email address.") 
        @Email(message = "Please enter a valid email address.")
        String email,

        @NotBlank(message = "Please enter your password.")
        String password
) {}
