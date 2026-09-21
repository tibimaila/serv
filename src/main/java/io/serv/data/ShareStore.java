package io.serv.data;

import io.serv.data.Store; 
import io.serv.domain.ShareLink;

public interface ShareStore extends Store<ShareLink, UUID> {
    
    Optional<ShareLink> findByToken(String token);

    List<ShareLink> findByResourceId(UUID resourceId);
}
