package io.serv.domain;

import java.util.UUID;

public class User {

    private final UUID id;
    private String email;
    private String passwordHash;
    private boolean rootUser;

    public User(UUID id, String email, String passwordHash, boolean rootUser) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.rootUser = rootUser;
    }

    public UUID id() { return id; }
    public void setEmail(String email) { this.email = email; }
    public String email() { return email; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }
    public String passwordHash() { return passwordHash; }
    public void setRootUser(boolean rootUser) { this.rootUser = rootUser; }
    public boolean isRootUser() { return rootUser; }
}
