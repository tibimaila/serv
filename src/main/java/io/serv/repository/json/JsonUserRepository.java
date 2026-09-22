package io.serv.repository.json;

import org.springframework.stereotype.Repository;
import jakarta.annotation.PostConstruct;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.serv.config.ServProperties;
import io.serv.domain.user.User;
import io.serv.repository.UserRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class JsonUserRepository implements UserRepository {

    private final ObjectMapper objectMapper;
    private final Path usersFile;

    private Map<UUID, User> usersById = new LinkedHashMap<>();

    /**
    * Creates a JSON user repository.
    *
    * @param servProperties Serv application configuration
    * @param objectMapper Jackson object mapper used for JSON persistence
    */
    public JsonUserRepository(ServProperties servProperties, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.usersFile = servProperties.registryDir().resolve("users.json");
    }

    /**
     * Loads users from the JSON file when the application starts.
     * 
     * @throws IllegalStateException when the users file cannot be read
     */
    @PostConstruct
    void loadUsers() {
        if (usersById.isEmpty()) {
            try {
                Files.createDirectories(usersFile.getParent());

                if (!Files.exists(usersFile)) {
                usersById = new LinkedHashMap<>();
                return;
                }

                List<User> users = objectMapper.readValue(usersFile.toFile(),
                        objectMapper.getTypeFactory().constructCollectionType(List.class, User.class));
                
                usersById = new LinkedHashMap<>();

                for (User user : users) {
                    usersById.put(user.id(), user);
                }

                } catch (IOException e) {
                throw new IllegalStateException("Failed to load users from JSON file: " + usersFile, e);
            }
        }
    }

    @Override
    public Optional<User> findById(UUID id) {
        if (id == null) { return Optional.empty(); }
        
        return Optional.ofNullable(usersById.get(id));
    }

    @Override
    public List<User> findAll() { return List.copyOf(usersById.values()); }

    @Override
    public Optional<User> findByEmail(String email) {
        if (email == null || email.isBlank()) { return Optional.empty(); }
        
        return usersById.values().stream()
        .filter(user -> user.email().equalsIgnoreCase(email))
        .findFirst();
    }

    /**
     * Checks whether a user exists with the specified email address.
     *
     * @param email the user's email address
     * @return true when a matching user exists
     */
    @Override 
    public boolean existsByEmail(String email) {return findByEmail(email).isPresent(); }

    @Override 
    public User save(User user) {
        if (user == null) { throw new IllegalArgumentException("User must not be null."); }

        usersById.put(user.id(), user);
        writeUsersToFile();

        return user;
    }

    @Override
    public void deleteById(UUID id) {
        if (id == null) { return; }

        if (usersById.remove(id) != null) {
            writeUsersToFile();
        }
    }

    /**
    * Saves the current users to the JSON file.
    *
    * @throws IllegalStateException when the users  when the users cannot be saved
    */
    private void writeUsersToFile() {
        try {
            Path tmp = Files.createTempFile(usersFile.getParent(), "users-", ".json.tmp");
            
            try {
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(tmp.toFile(), new ArrayList<>(usersById.values()));
            } catch (IOException e) {
                throw new IllegalStateException("Failed to save users to JSON file: " + usersFile, e);
            }

            Files.move(tmp, usersFile, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to save users to JSON file: " + usersFile, e);
        }
    }


}
