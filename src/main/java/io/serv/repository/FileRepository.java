package io.serv.repository;

import io.serv.domain.FileObject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FileRepository extends Repository<FileObject, UUID> {

    List<FileObject> findByDriveId(UUID driveId);

    Optional<FileObject> findByFilePath(UUID driveId, String path);
}
