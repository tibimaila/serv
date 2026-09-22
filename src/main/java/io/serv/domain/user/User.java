package io.serv.domain;

import io.serv.domain.user.Role;

import java.util.UUID;
import java.time.Instant;
import java.util.Set;

public record User(
        UUID id,
        String email,
        String passwordHash,
        String displayName,
        Set<Role> roles,
        boolean mfaEnabled,
        String totpSecret,
        int failedLoginAttempts,
        Instant lockedUntil,
        Instant createdAt
) {

    public User{
        if (id == null) { throw new IllegalArgumentException("User ID is required."); }
        if (email == null) { throw new IllegalArgumentException("User email is required."); }
        if (passwordHash == null) { throw new IllegalArgumentException("User password hash is required."); }

        roles = roles == null ? Set.of() : Set.copyOf(roles);
    }

    public static User create(UUID id, String email, String passwordHash, String displayName) {
        return new User(UUID.randomUUID(), email, passwordHash, displayName, Set.of(Role.USER), false, null, 0, null, Instant.now());
    }

    public boolean isLocked() {
        return lockedUntil != null && lockedUntil.isAfter(Instant.now());
    }

    public User withPasswordHash(String newPasswordHash) {
        return new User(id, email, newPasswordHash, displayName, roles, mfaEnabled, totpSecret, failedLoginAttempts, lockedUntil, createdAt);
    }

    public User withFailedLoginAttempts(int newFailedLoginAttempts, Instant newLockedUntil) {
        return new User(id, email, passwordHash, displayName, roles, mfaEnabled, totpSecret, newFailedLoginAttempts, newLockedUntil, createdAt);
    }

    public User withMfaEnabled(boolean newMfaEnabled, String newTotpSecret) {
        return new User(id, email, passwordHash, displayName, roles, newMfaEnabled, newTotpSecret, failedLoginAttempts, lockedUntil, createdAt);
    }

}
