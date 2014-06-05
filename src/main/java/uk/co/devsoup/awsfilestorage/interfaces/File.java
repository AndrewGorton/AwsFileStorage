package uk.co.devsoup.awsfilestorage.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class File {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @JsonProperty
    public String getName() {
        return name;
    }
}
