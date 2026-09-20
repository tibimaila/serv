package io.serv.data;

import io.serv.domain.Drive;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DriveStore {
    
    Optional<Drive> findById(UUID id);

    List<Drive> findAll();

    List<Drive> findByOwnerId(UUID ownerId);

    Drive save(Drive drive);

    void delete(UUID id);
}
