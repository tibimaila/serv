package io.serv.data;

import io.serv.domain.FileObject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileStore extends Store<FileObject, UUID> {

    List<FileObject> findByDriveId(UUID driveId);

    Optional<FileObject> findByFilePath(UUID driveId, String path);
}
