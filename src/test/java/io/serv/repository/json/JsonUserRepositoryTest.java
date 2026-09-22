package io.serv.repository.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.serv.config.ServProperties;
import io.serv.domain.user.Role;
import io.serv.domain.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import org.springframework.util.unit.DataSize;

import java.nio.file.Path;
import java.nio.file.Files;
import java.time.Duration;
import java.util.Set;
import java.time.Instant;
import java.util.UUID;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonUserRepositoryTest {
    @TempDir
    Path tempDir;

    private JsonUserRepository userRepository;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        ServProperties servProperties = new ServProperties(
            tempDir,
            new ServProperties.Jwt(
                "12345678901234567890123456789012",
                "serv",
                Duration.ofMinutes(15),
                Duration.ofDays(7)
            ),
            new ServProperties.Storage(
                DataSize.ofGigabytes(10),
                DataSize.ofGigabytes(8),
                Duration.ofHours(24)

            ),
            new ServProperties.Security(
                600000,
                5,
                Duration.ofMinutes(5)
            ),
            new ServProperties.Cors(
                List.of("http://localhost:5173")
            )
        );
        userRepository = new JsonUserRepository(servProperties, objectMapper);
        userRepository.loadUsers();
    }

    @Test
    void saveShouldStoreUser() {
        UUID userId = UUID.randomUUID();
        User user = new User(userId, "test-user@serv.local", "hashed-password", "Test User", Set.of(Role.USER), false, null, 0, null, Instant.now());
        
        User savedUser = userRepository.save(user);

        Path usersFile = tempDir.resolve("users").resolve("users.json");

        assertEquals(user, savedUser);
        assertTrue(Files.exists(usersFile));
        assertTrue(Files.readString(usersFile).contains(userId.toString()));
    }
}
