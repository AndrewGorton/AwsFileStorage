package uk.co.devsoup.awsfilestorage.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Folder {
    private String name;

    public Folder(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
