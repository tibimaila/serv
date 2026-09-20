package io.serv.domain;

import java.util.UUID;

public class Drive {

    private final UUID id;
    private final UUID ownerId;
    private final String storagePath;

    private String name;
    private long sizeLimit;
    private boolean publicRead;
    private boolean publicList;

    public Drive(UUID id, String name, UUID ownerId, String storagePath, long sizeLimit) {
        this.id = id;
        this.name = name;
        this.ownerId = ownerId;
        this.storagePath = storagePath;
        this.sizeLimit = sizeLimit;

        // Drives are private by default.
        this.publicRead = false;
        this.publicList = false;
    }

    public UUID id() { return id; }
    public void setName(String name) { this.name = name; }
    public String name() { return name; }
    public UUID ownerId() { return ownerId; }
    public String storagePath() { return storagePath; }
    public void setSizeLimit(long sizeLimit) { this.sizeLimit = sizeLimit; }
    public long sizeLimit() { return sizeLimit; }
    public void setPublicRead(boolean publicRead) { this.publicRead = publicRead; }
    public boolean isPublicRead() { return publicRead; }
    public void setPublicList(boolean publicList) { this.publicList = publicList; }
    public boolean isPublicList() { return publicList; }
}
