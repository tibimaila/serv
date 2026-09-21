package io.serv.domain;

import java.util.Objects;
import java.util.UUID;

public class Policy {
    
    private final UUID resourceId;
    
    private boolean publicRead;
    private boolean publicList;

    private boolean allowRead;
    private boolean allowWrite;
    private boolean allowDelete;

    public Policy(UUID resourceId) {
        this.resourceId = Objects.requireNonNull(
            resourceId, "resourceId must not be null");
    }

    public UUID resourceId() { return resourceId; }
    public boolean isPublicRead() { return publicRead; }
    public boolean isPublicList() { return publicList; }
    public boolean canRead() { return allowRead; }
    public boolean canWrite() { return allowWrite; }
    public boolean canDelete() { return allowDelete; }
    public void allowRead() { this.allowRead = true; }
    
    public void denyRead() { 
        this.allowRead = false;
        this.allowWrite = false;
        this.allowDelete = false; 
    }
    
    public void allowWrite() { 
        if(!this.allowRead) {
            throw new IllegalStateException("allowWrite requires allowRead to be enabled");
        }
        this.allowWrite = true;
     }

    public void denyWrite() { 
        this.allowWrite = false;
        this.allowDelete = false;
    }

    public void allowDelete() { 
       if (!allowWrite) {
            throw new IllegalStateException("allowDelete requires allowWrite to be enabled");
        }
        this.allowDelete = true;
    }

    public void denyDelete() { this.allowDelete = false; }
    public void enablePublicRead() { this.publicRead = true; }
    public void disablePublicRead() { this.publicRead = false; }
    public void enablePublicList() { this.publicList = true; }
    public void disablePublicList() { this.publicList = false; }
}
  
