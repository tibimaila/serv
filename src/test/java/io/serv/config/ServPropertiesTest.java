package io.serv.config;

import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class ServPropertiesTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TestConfig.class)
            .withPropertyValues(
                            "serv.data-dir=/tmp/data",
                            "serv.jwt.secret=abcdefghijklmnopqrstuvwxyz123456",
                            "serv.jwt.issuer=serv",
                            "serv.jwt.access-token-ttl=15m",
                            "serv.jwt.refresh-token-ttl=7d",

                            "serv.storage.default-drive-quota=10GB",
                            "serv.storage.chunk-size=8MB",
                            "serv.storage.stale-upload-ttl=24h",

                            "serv.security.pbkdf2-iterations=600000",
                            "serv.security.max-failed-logins=5",
                            "serv.security.lockout-duration=5m",

                            "serv.cors.allowed-origins=http://localhost:5173"
            );

    @Test
    void testDefaultProperties() {
        contextRunner.run(context -> { ServProperties properties = context.getBean(ServProperties.class);
            assertThat(properties.dataDir()).isEqualTo(Path.of("/tmp/data"));
            assertThat(properties.jwt().secret()).isEqualTo("abcdefghijklmnopqrstuvwxyz123456");
            assertThat(properties.jwt().issuer()).isEqualTo("serv");
            assertThat(properties.jwt().accessTokenTtl()).isEqualTo(java.time.Duration.ofMinutes(15));
            assertThat(properties.jwt().refreshTokenTtl()).isEqualTo(java.time.Duration.ofDays(7));
            assertThat(properties.storage().defaultDriveQuota()).isEqualTo(org.springframework.util.unit.DataSize.ofGigabytes(10));
            assertThat(properties.storage().chunkSize()).isEqualTo(org.springframework.util.unit.DataSize.ofMegabytes(8));
            assertThat(properties.storage().staleUploadTtl()).isEqualTo(java.time.Duration.ofHours(24));
            assertThat(properties.security().pbkdf2Iterations()).isEqualTo(600000);
            assertThat(properties.security().maxFailedLogins()).isEqualTo(5);
            assertThat(properties.security().lockoutDuration()).isEqualTo(java.time.Duration.ofMinutes(5));
            assertThat(properties.cors().allowedOrigins()).containsExactly("http://localhost:5173");
        });
    }

    @Test
    void testPropertiesBinding() {
        contextRunner.withPropertyValues(
                        "serv.data-dir=./data",

                        "serv.jwt.secret=12345678901234567890123456789012",
                        "serv.jwt.issuer=test-serv",
                        "serv.jwt.access-token-ttl=30m",
                        "serv.jwt.refresh-token-ttl=14d",

                        "serv.storage.default-drive-quota=20GB",
                        "serv.storage.chunk-size=16MB",
                        "serv.storage.stale-upload-ttl=48h",

                        "serv.security.pbkdf2-iterations=700000",
                        "serv.security.max-failed-logins=10",
                        "serv.security.lockout-duration=10m",

                        "serv.cors.allowed-origins=http://localhost:3000"
        ).run(context -> {
            ServProperties properties = context.getBean(ServProperties.class);
            assertThat(properties.dataDir()).isEqualTo(Path.of("data"));
            assertThat(properties.jwt().secret()).isEqualTo("12345678901234567890123456789012");
            assertThat(properties.jwt().issuer()).isEqualTo("test-serv");
            assertThat(properties.jwt().accessTokenTtl()).isEqualTo(java.time.Duration.ofMinutes(30));
            assertThat(properties.jwt().refreshTokenTtl()).isEqualTo(java.time.Duration.ofDays(14));
            assertThat(properties.storage().defaultDriveQuota()).isEqualTo(org.springframework.util.unit.DataSize.ofGigabytes(20));
            assertThat(properties.storage().chunkSize()).isEqualTo(org.springframework.util.unit.DataSize.ofMegabytes(16));
            assertThat(properties.storage().staleUploadTtl()).isEqualTo(java.time.Duration.ofHours(48));
            assertThat(properties.security().pbkdf2Iterations()).isEqualTo(700000);
            assertThat(properties.security().maxFailedLogins()).isEqualTo(10);
            assertThat(properties.security().lockoutDuration()).isEqualTo(java.time.Duration.ofMinutes(10));
            assertThat(properties.cors().allowedOrigins()).containsExactly("http://localhost:3000");
        });
    }

    @EnableConfigurationProperties(ServProperties.class)
    static class TestConfig {}
}
