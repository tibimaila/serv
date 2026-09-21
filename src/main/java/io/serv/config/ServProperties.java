package io.serv.config;

import java.nio.file.Path;
import java.time.Duration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.util.unit.DataSize;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Validated
@ConfigurationProperties(prefix = "serv")
public record ServProperties(
    @NotNull @DefaultValue("./data") Path dataDir,
    @Valid @DefaultValue Jwt jwt,
    @Valid @DefaultValue Storage storage,
    @Valid @DefaultValue Security security,
    @Valid @DefaultValue Cors cors
) {
    // Derived directories based on the dataDir property
    
    public Path registryDir() { return dataDir.resolve("users"); }
    public Path drivesDir() { return dataDir.resolve("drives"); }
    public Path tmpDir() { return dataDir.resolve("tmp"); }


    // Nested records for structured configuration properties

    public record Jwt(
        /* HMAC signing key. Required: at least 32 characters (256 bits) */
        @NotBlank @Size(min = 32, message = "serv.jwt.secret must be at least 32 characters") String secret,
        @NotBlank @DefaultValue("serv") String issuer,
        @NotNull @DefaultValue("15m") Duration accessTokenTtl,
        @NotNull @DefaultValue("7d") Duration refreshTokenTtl
    ) {}

    public record Storage(
        /* Default quota given to each new drive */ 
        @NotNull @DefaultValue("10GB") DataSize defaultDriveQuota,
        /* Multipart upload chunk size for large files */                
        @NotNull @DefaultValue("8MB") DataSize chunkSize,
        /* Time-to-live for stale multipart uploads (uploads that were not completed) */      
        @NotNull @DefaultValue("24h") Duration staleUploadTtl    
    ) {}

    public record Security(
        /* PBKDF2-HMAC-SHA256 iterations */
        @Min(100000) @DefaultValue("600000") int pbkdf2Iterations,
        /* Maximum number of failed login attempts before account lockout */
        @Min(1) @DefaultValue("5") int maxFailedLogins,
        /* Duration for which an account is locked out after failed login attempts */
        @NotNull @DefaultValue("5m") Duration lockoutDuration
    ) {}

    public record Cors(
        /* Origins allowed to call the API */
        @NotNull @DefaultValue({"http://localhost:5173"}) List<String> allowedOrigins
    ) {}
}