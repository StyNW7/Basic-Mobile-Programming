package com.example.musicinfoplayer;

import org.json.JSONObject;

public class Track {
    private String title;
    private String preview;
    private Artist artist;

    public String getTitle() { return title; }
    public String getPreview() { return preview; }
    public Artist getArtist() { return artist; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
