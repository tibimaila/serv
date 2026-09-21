package io.serv;

import io.serv.config.ServProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(ServProperties.class)
public class ServApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServApplication.class, args);
    }
}
