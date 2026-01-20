package com.example.latihanuas.model;

public class Joke {

    private String type;
    private String setup;
    private String punchline;

    public Joke(String type, String setup, String punchline) {
        this.type = type;
        this.setup = setup;
        this.punchline = punchline;
    }

    public String getType() {
        return type;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }

}
