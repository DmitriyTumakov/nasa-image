package ru.netology;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NASA {
    private final String copyright;
    private final String date;
    private final String explanation;
    private final String url;
    private final String hdurl;
    private final String mediaType;
    private final String serviceVersion;
    private final String title;

    public NASA(
            @JsonProperty("copyright") String copyright,
            @JsonProperty("date") String date,
            @JsonProperty("explanation") String explanation,
            @JsonProperty("url") String url,
            @JsonProperty("hdurl") String hdurl,
            @JsonProperty("media_type") String mediaType,
            @JsonProperty("service_version") String serviceVersion,
            @JsonProperty("title") String title
    ) {
        this.copyright = copyright;
        this.date = date;
        this.explanation = explanation;
        this.url = url;
        this.hdurl = hdurl;
        this.mediaType = mediaType;
        this.serviceVersion = serviceVersion;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }
}
