package io.serv.domain;

import java.time.Instant;
import java.util.UUID;

public class ShareLink {
    private final UUID id;
    private UUID resourceId;
    private String link;
    private Instant createdBy;
    private final String token;
    private final Instant expiresAt;
    private final boolean readOnly;

    public ShareLink(UUID id, UUID resourceId, String link, String token, Instant expiresAt, boolean readOnly) {
        this.id = id;
        this.resourceId = resourceId;
        this.link = link;
        this.createdBy = Instant.now();
        this.token = token;
        this.expiresAt = expiresAt;
        this.readOnly = readOnly;
    }

    public UUID id() { return id; }
    public UUID resourceId() { return resourceId; }
    public void setLink(String link) { this.link = link; }
    public String link() { return link; }
    public Instant createdBy() { return createdBy; }
    public String token() { return token; }
    public Instant expiresAt() { return expiresAt; }
    public boolean isExpired() { return expiresAt != null && Instant.now().isAfter(expiresAt); }
    public boolean isReadOnly() { return readOnly; }
}
