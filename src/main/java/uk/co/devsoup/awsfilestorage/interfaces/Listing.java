package uk.co.devsoup.awsfilestorage.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Listing {
    private List<File> files;
    private List<Folder> folders;
    private String path;
    private Boolean pathExists;

    public Listing(String path, List<File> files, List<Folder> folders, Boolean pathExists) {
        this.path = path;
        this.files = files;
        this.folders = folders;
        this.pathExists = pathExists;
    }

    @JsonProperty
    public String getPath() {
        return path;
    }

    @JsonProperty
    public List<File> getFiles() {
        return files;
    }

    @JsonProperty
    public List<Folder> getFolders() {
        return folders;
    }

    @JsonProperty
    public Boolean getPathExists() {
        return pathExists;
    }

}
