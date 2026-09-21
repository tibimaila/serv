package io.serv.data;

import io.serv.domain.Folder;

public interface FolderStore extends Store<Folder, UUID> {

    List<Folder> findByDriveId(UUID driveId);

    Optional<Folder> findByFolderPath(UUID driveId, String path);
}
