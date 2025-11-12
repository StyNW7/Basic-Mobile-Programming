package edu.bluejack25_1.latihan_uts.model;

public class Note {

    private long id;
    private String title;

    private String body;

    private long lastEditedAt;

    public Note(){}

    public Note(long id, String title, String body, long lastEditedAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.lastEditedAt = lastEditedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getLastEditedAt() {
        return lastEditedAt;
    }

    public void setLastEditedAt(long lastEditedAt) {
        this.lastEditedAt = lastEditedAt;
    }

}
