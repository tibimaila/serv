package io.serv.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;

public class ServPropertiesTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withUserConfiguration(TestConfig.class)
            .withPropertyValues(
                            "serv.data-dir=/tmp/data",
                            "serv.storage-root=/tmp/drive-data",
                            "serv.presign-ttl-seconds=900",
                            "serv.multipart-threshold=104857600",
                            "serv.part-size=10485760",
                            "serv.max-drive-size=1000000000",
                            "serv.root-email=admin@serv.local",
                            "serv.root-password=admin",
                            "serv.cors-origin=http://localhost:5173"
            );

    @Test
    void testPropertiesBinding() {
        contextRunner.run(context -> { ServProperties properties = context.getBean(ServProperties.class);
            assertThat(properties.DataDir()).isEqualTo("/tmp/data");
            assertThat(properties.StorageRoot()).isEqualTo("/tmp/drive-data");
            assertThat(properties.PresignTtlSeconds()).isEqualTo(900);
            assertThat(properties.MultipartThreshold()).isEqualTo(104857600);
            assertThat(properties.PartSize()).isEqualTo(10485760);
            assertThat(properties.MaxDriveSize()).isEqualTo(1000000000);
            assertThat(properties.RootEmail()).isEqualTo("admin@serv.local");
            assertThat(properties.RootPassword()).isEqualTo("admin");
            assertThat(properties.CorsOrigin()).isEqualTo("http://localhost:5173");
        });
    }
    @EnableConfigurationProperties(ServProperties.class)
    static class TestConfig {}
}
