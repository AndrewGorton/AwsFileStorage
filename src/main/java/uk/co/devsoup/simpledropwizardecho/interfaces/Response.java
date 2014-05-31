package uk.co.devsoup.simpledropwizardecho.interfaces;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Response {
    private String guid;
    private String echo;

    public Response() {

    }

    public Response(String guid, String echo) {
        this.guid = guid;
        this.echo = echo;
    }

    @JsonProperty
    public String getGuid() {
        return guid;
    }

    @JsonProperty
    public String getEcho() {
        return echo;
    }
}
