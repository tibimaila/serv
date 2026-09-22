package io.serv.repository;

import io.serv.domain.Drive;

import java.util.List;
import java.util.UUID;

public interface DriveRepository extends Repository<Drive, UUID> {

    List<Drive> findByOwnerId(UUID ownerId);
}
