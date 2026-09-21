package io.serv;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "serv.jwt.secret=12345678901234567890123456789012")
class ApplicationTest {
    @Test
    void contextLoads() {
    }
}