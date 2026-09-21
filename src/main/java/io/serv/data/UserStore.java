package io.serv.data;

import io.serv.domain.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserStore extends Store<User, UUID> {

    Optional<User> findByEmail(String email);
}
