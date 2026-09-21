package io.serv.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Validated
@ConfigurationProperties(prefix = "serv")
public class ServProperties {

    private static final long DEFAULT_PRESIGN_TTL_SECONDS = 900;
    private static final long DEFAULT_MULTIPART_THRESHOLD_BYTES = 100 * 1024 * 1024;
    private static final long DEFAULT_PART_SIZE_BYTES = 10 * 1024 * 1024;
    private static final long DEFAULT_MAX_DRIVE_SIZE_BYTES = 1_000_000_000L;

    @NotBlank
    private String dataDir = "/tmp/data";
    @NotBlank
    private String storageRoot = "/tmp/drive-data";
    @Min(1)
    private long presignTtlSeconds = DEFAULT_PRESIGN_TTL_SECONDS;
    @Min(1)
    private long multipartThreshold = DEFAULT_MULTIPART_THRESHOLD_BYTES;
    @Min(1)
    private long partSize = DEFAULT_PART_SIZE_BYTES;
    @Min(1)
    private long maxDriveSize = DEFAULT_MAX_DRIVE_SIZE_BYTES;
    @NotBlank
    private String rootEmail = "admin@serv.local";
    private String rootPassword;
    @NotBlank
    private String corsOrigin = "http://localhost:5173";

    public void setDataDir(String dataDir) { this.dataDir = dataDir; }

    public String DataDir() { return dataDir; }

    public String StorageRoot() { return storageRoot; }

    public void setStorageRoot(String storageRoot) { this.storageRoot = storageRoot; }

    public long PresignTtlSeconds() { return presignTtlSeconds; }

    public void setPresignTtlSeconds(long presignTtlSeconds) { this.presignTtlSeconds = presignTtlSeconds; }

    public long MultipartThreshold() { return multipartThreshold; }

    public void setMultipartThreshold(long multipartThreshold) { this.multipartThreshold = multipartThreshold; }

    public long PartSize() { return partSize; }

    public void setPartSize(long partSize) { this.partSize = partSize; }

    public long MaxDriveSize() { return maxDriveSize; }

    public void setMaxDriveSize(long maxDriveSize) { this.maxDriveSize = maxDriveSize; }

    public String RootEmail() { return rootEmail; }

    public void setRootEmail(String rootEmail) { this.rootEmail = rootEmail; }

    public String RootPassword() { return rootPassword; }

    public void setRootPassword(String rootPassword) { this.rootPassword = rootPassword; }

    public String CorsOrigin() { return corsOrigin; }

    public void setCorsOrigin(String corsOrigin) { this.corsOrigin = corsOrigin; }
}