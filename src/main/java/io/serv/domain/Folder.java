package io.serv.domain;

import java.time.Instant;
import java.util.UUID;

public class Folder {
    private final UUID id;
    private String name;
    private String path;
    private UUID driveId;
    private Instant createdAt;

    public Folder(UUID id, String name, String path, UUID parentId, UUID driveId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.driveId = driveId;
        this.createdAt = Instant.now();
    }

    public UUID id() { return id; }
    public void setName(String name) { this.name = name; }
    public String name() { return name; }
    public void setDriveId(UUID driveId) { this.driveId = driveId; }
    public UUID driveId() { return driveId; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
    public Instant createdAt() { return createdAt; }
}
