package io.serv.domain;

import java.time.Instant;
import java.util.UUID;

public class FileObject {
    private final UUID id;
    private final UUID ownerId;    
    private final Instant createdAt;

    private UUID driveId;
    private String name;
    private String path;
    private long size;
    private String contentType;
    private Instant updatedAt;
    

    public FileObject(UUID id, String name, UUID driveId, String path, UUID ownerId, long size, String contentType) {
        this.id = id;
        this.name = name;
        this.driveId = driveId;
        this.path = path;
        this.ownerId = ownerId;
        this.size = size;
        this.contentType = contentType;
        this.createdAt = Instant.now();
        this.updatedAt = this.createdAt;
    }

    public UUID id() { return id; }
    public void setName(String name) { 
        this.name = name;
        touch();  
    }
    public String name() { return name; }
    public UUID driveId() { return driveId; }
    public String path() { return path; }
    public UUID ownerId() { return ownerId; }
    public String contentType() { return contentType; }
    public Instant createdAt() { return createdAt; }
    public Instant updatedAt() { return updatedAt; }

    public void rename(String name) {
        this.name = name;
        touch();
    }

    public void moveTo(UUID driveId, String path) {
        this.driveId = driveId;
        this.path = path;
        touch();
    }

    // Call touch() to update updatedAt  
    private void touch() { this.updatedAt = Instant.now(); }
}
