package io.serv.repository;

import io.serv.domain.ShareLink;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ShareRepository extends Repository<ShareLink, UUID> {

    Optional<ShareLink> findByToken(String token);

    List<ShareLink> findByResourceId(UUID resourceId);
}
