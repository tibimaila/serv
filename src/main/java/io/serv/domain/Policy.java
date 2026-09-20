package io.serv.domain;

import java.util.UUID;

public class Policy {
    private final UUID resourceId;
    private boolean publicRead;
    private boolean publicList;

    private boolean allowRead;
    private boolean allowWrite;
    private boolean allowDelete;

    public Policy(UUID resourceId) {
        this.resourceId = resourceId;   // Resources are private by default
        this.publicRead = false;
        this.publicList = false;
        this.allowRead = false;
        this.allowWrite = false;
        this.allowDelete = false;
    }

    public UUID resourceId() { return resourceId; }
    public boolean isPublicRead() { return publicRead; }
    public void setPublicRead(boolean publicRead) { this.publicRead = publicRead; }
    public boolean isPublicList() { return publicList; }
    public void setPublicList(boolean publicList) { this.publicList = publicList; }
    public boolean isAllowRead() { return allowRead; }
    public void setAllowRead(boolean allowRead) { this.allowRead = allowRead; }
    public boolean isAllowWrite() { return allowWrite; }
    public void setAllowWrite(boolean allowWrite) { this.allowWrite = allowWrite; }
    public boolean isAllowDelete() { return allowDelete; }
    public void setAllowDelete(boolean allowDelete) { this.allowDelete = allowDelete; }
}
