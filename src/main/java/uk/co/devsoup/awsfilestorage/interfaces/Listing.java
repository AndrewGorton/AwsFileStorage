package uk.co.devsoup.awsfilestorage.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Listing {
    private List<String> keys;

    public Listing(List<String> keys) {
        this.keys = keys;
    }

    @JsonProperty
    public List<String> getKeys() {
        return keys;
    }
}
