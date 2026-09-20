package io.serv.data;

import io.serv.domain.FileObject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileStore {

    Optional<FileObject> findById(UUID id);

    List<FileObject> findByDriveId(UUID driveId);

    Optional<FileObject> findByDriveIdAndPath(UUID driveId, String path);

    FileObject save(FileObject fileObject);

    void deleteById(UUID id);
}
