package net.thucydides.showcase.simple.pages;

public class ArtifactEntry {
    private final String groupId;
    private final String artifactId;
    private final String latestVersion;

    public ArtifactEntry(String groupId, String artifactId, String latestVersion) {
        this.groupId = groupId;
        this.artifactId = artifactId;
        this.latestVersion = latestVersion;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public String getLatestVersion() {
        return latestVersion;
    }
}
