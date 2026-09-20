package io.serv.domain;

import java.time.Instant;
import java.util.UUID;

public class FileObject {
    private final UUID id;
    private String name;
    private UUID driveId;
    private String path;
    private UUID ownerId;
    private long size;
    private String contentType;
    private Instant createdAt;
    private Instant updatedAt;
    

    public FileObject(UUID id, String name, UUID driveId, String path, UUID ownerId, long size, String contentType, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.name = name;
        this.driveId = driveId;
        this.path = path;
        this.ownerId = ownerId;
        this.size = size;
        this.contentType = contentType;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID id() { return id; }
    public void setName(String name) { this.name = name; }
    public String name() { return name; }
    public UUID driveId() { return driveId; }
    public String path() { return path; }
    public void setOwnerId(UUID ownerId) { this.ownerId = ownerId; }
    public UUID ownerId() { return ownerId; }
    public void setSize(long size) { 
        this.size = size;
        this.updatedAt = Instant.now(); // Update updatedAt whenever size is set  
     }
    public long size() { return size; }
    public void setContentType(String contentType) { 
        this.contentType = contentType;
        this.updatedAt = Instant.now(); // Update updatedAt whenever contentType is set
    }
    public String contentType() { return contentType; }
    public void setCreatedAt(Instant createdAt) { 
        this.createdAt = createdAt;
        this.updatedAt = Instant.now(); // Update updatedAt whenever createdAt is set
        }
    public Instant createdAt() { return createdAt; }
    public void setUpdatedAt(Instant updatedAt) { this.updatedAt = updatedAt; }
    public Instant updatedAt() { return updatedAt; }

}
