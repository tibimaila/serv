package io.serv.repository.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.serv.config.ServProperties;
import io.serv.domain.user.Role;
import io.serv.domain.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import org.springframework.util.unit.DataSize;

import java.io.IOException;
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
    Path temp;

    private JsonUserRepository userRepository;

    @BeforeEach
    void setUp() {
        ObjectMapper objectMapper = new ObjectMapper();
        // Register Jackson modules to serialize and deserialize Instant fields
        objectMapper.findAndRegisterModules();
        // Create application properties using the temporary directory
        ServProperties servProperties = new ServProperties(
            temp,
            new ServProperties.Jwt(
                "12345678901234567890123456789012",
                "serv",
                Duration.ofMinutes(15),
                Duration.ofDays(7)
            ),
            new ServProperties.Storage(
                DataSize.ofGigabytes(10),
                DataSize.ofMegabytes(8),
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
        // Load existing users from the temporary directory.
        userRepository.loadUsers();
    }

    @Test
    void saveShouldStoreUser() throws IOException {
        UUID userId = UUID.randomUUID();
        User user = createTestUser(userId, "eve@test.serv", "Eve");
        
        User savedUser = userRepository.save(user);

        Path usersFile = temp.resolve("users").resolve("users.json");

        assertEquals(user, savedUser);
        assertTrue(Files.exists(usersFile));
        
        String json = Files.readString(usersFile);
        
        assertTrue(json.contains(userId.toString()));
        assertTrue(json.contains("eve@test.serv"));
        assertTrue(json.contains("Eve"));
        assertTrue(json.contains("hashed-password"));
    }

        @Test
    void findByIdShouldReturnUser() {
        UUID userId = UUID.randomUUID();

        User user = createTestUser(userId,"tom@test.serv","Tom");
        userRepository.save(user);

        var result = userRepository.findById(userId);

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }
    
        @Test
    void findByIdShouldReturnEmptyWhenUserDoesNotExist() {
        UUID userId = UUID.randomUUID();

        var result = userRepository.findById(userId);

        assertTrue(result.isEmpty());
    }

        @Test
    void findAllShouldReturnAllUsers() {
        User user1 = createTestUser(UUID.randomUUID(), "ana@test.serv", "Ana");
        User user2 = createTestUser(UUID.randomUUID(), "nancy@test.serv", "Nancy");
        User user3 = createTestUser(UUID.randomUUID(), "robert@test.serv", "Robert");

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        List<User> users = userRepository.findAll();

        assertEquals(3, users.size());
        assertTrue(users.contains(user1));
        assertTrue(users.contains(user2));
    }

    @Test
    void findByEmailShouldFindUserIgnoringCase() {
        User user = createTestUser(UUID.randomUUID(), "Robert@test.serv", "Robert");
        userRepository.save(user);

        var result = userRepository.findByEmail("robert@test.serv");

        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }

    @Test
    void findByEmailShouldReturnEmptyForUnknownEmail() {
        User user = createTestUser(UUID.randomUUID(), "ana@serv", "Ana");
        userRepository.save(user);

        var result = userRepository.findByEmail("ana@serv.local");

        assertTrue(result.isEmpty());
    }

    @Test
    void existsByEmailShouldReturnTrueForExistingUser() {
        User user = createTestUser(UUID.randomUUID(), "bob@test.serv", "Bob");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("bob@test.serv");

        assertTrue(exists);
    }

    @Test
    void existsByEmailShouldReturnFalseForExistingUser() {
        User user = createTestUser(UUID.randomUUID(), "bob@test.serv.", "Bob");
        userRepository.save(user);

        boolean exists = userRepository.existsByEmail("james@test.serv");

        assertFalse(exists);
    }

    @Test
    void deleteByIdShouldRemoveUser() {
        UUID userId = UUID.randomUUID();

        User user = createTestUser(userId, "james@serv.local", "Delete User");
        userRepository.save(user);

        // Verify the user exists before deletion.
        assertTrue(userRepository.findById(userId).isPresent());

        userRepository.deleteById(userId);

        assertTrue(userRepository.findById(userId).isEmpty());
        assertTrue(userRepository.findAll().isEmpty());
    }
    
    private User createTestUser(UUID userId, String email, String displayName) {
        return new User(userId, email, "hashed-password", displayName, Set.of(Role.USER), false, null, 0, null, Instant.now());
    }
        
}
