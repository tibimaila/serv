package io.serv.storage;

import io.serv.domain.User;
import io.serv.data.UserStore;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class FileUserStore implements UserStore {

    @Override
    public Optional<User> findById(UUID userId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return List.of();
    }

    @Override
    public User save(User user) {
        return user;
    }

    @Override
    public void deleteById(UUID userId) {
    }
}
