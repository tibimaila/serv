package io.serv.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "serv")
public class Properties {
    private String dataDir = "./data";
    private String storageRoot = "./drive-data";
    private long presignTtlSeconds = 900;
    private long multipartThreshold = 104857600;
    private long partSize = 10485760;
    private long maxDriveSize = 0;
    private String rootEmail = "admin@serv.local";
    private String rootPassword = "admin";
    private String corsOrigin = "http://localhost:5173";

    public void setDataDir(String dataDir) { this.dataDir = dataDir; }

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