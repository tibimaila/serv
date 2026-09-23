package io.serv.api.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
        @NotBlank(message = "Please enter your email address.")
        @Email(message = "Please enter a valid email address.")
        String email,

        @NotBlank(message = "Please enter a password.")
        @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters.")
        String password,

        @NotBlank(message = "Please enter your display name.")
        @Size(min = 1,max = 100, message = "Display name must be between 1 and 100 characters."
        )
        String displayName 
) {}

