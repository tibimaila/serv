package io.serv.data;

import io.serv.domain.Drive;

import java.util.List;
import java.util.UUID;

public interface DriveStore extends Store<Drive, UUID> {

    List<Drive> findByOwnerId(UUID ownerId);
}
