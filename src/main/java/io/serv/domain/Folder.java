package io.serv.domain;

import java.time.Instant;
import java.util.UUID;

public class Folder {
    private final UUID id;
    private final Instant createdAt;

    private String name;
    private String path;
    private UUID driveId;
    private UUID parentId;
    private Instant updatedAt;

    public Folder(UUID id, String name, String path, UUID parentId, UUID driveId) {
        this.id = id;
        this.name = name;
        this.path = path;
        this.parentId = parentId;
        this.driveId = driveId;
        this.createdAt = Instant.now();
        this.updatedAt = Instant.now();
    }

    public UUID id() { return id; }
    public void setName(String name) { this.name = name; }
    public String name() { return name; }
    public String path() { return path; }
    public UUID parentId() { return parentId; }
    public UUID driveId() { return driveId; }
    public Instant createdAt() { return createdAt; }
    public Instant updatedAt() { return updatedAt; }
    
    public void rename(String newName) {
        this.name = newName;
        touch();
    }
    public void moveTo(String newPath, UUID newParentId, UUID newDriveId) {
        this.path = newPath;
        this.parentId = newParentId;
        this.driveId = newDriveId;
        touch();
    }

    public void touch() { this.updatedAt = Instant.now(); }
}
