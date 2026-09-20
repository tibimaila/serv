package io.serv.data;

import io.serv.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserStore {

    Optional<User> findById(UUID userId);

    Optional<User> findByEmail(String email);

    List<User> findAll();

    User save(User user);

    void deleteById(UUID userId);
}
