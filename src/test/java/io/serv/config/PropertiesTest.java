package io.serv.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class PropertiesTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(Properties.class)
            .withPropertyValues(
                            "serv.data-dir=/tmp/data",
                            "serv.storage-root=/tmp/drive-data",
                            "serv.presign-ttl-seconds=1200",
                            "serv.max-drive-size=500000000",
                            "serv.cors-origin=http://localhost:3000"
            );

    @Test
    void testPropertiesBinding() {
        contextRunner.run(context -> {
            Properties properties = context.getBean(Properties.class);
            assertThat(properties.DataDir()).isEqualTo("/tmp/data");
            assertThat(properties.StorageRoot()).isEqualTo("/tmp/drive-data");
            assertThat(properties.PresignTtlSeconds()).isEqualTo(1200);
            assertThat(properties.MaxDriveSize()).isEqualTo(500000000);
            assertThat(properties.CorsOrigin()).isEqualTo("http://localhost:3000");
        });
    }
    @EnableConfigurationProperties(Properties.class)
    static class TestConfig {}
}
