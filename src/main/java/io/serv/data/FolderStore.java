package io.serv.data;

import io.serv.domain.Folder;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FolderStore extends Store<Folder, UUID> {

    List<Folder> findByDriveId(UUID driveId);

    Optional<Folder> findByFolderPath(UUID driveId, String path);
}
