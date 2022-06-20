package com.training.MusicPlayer.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Genre {
    private String id;
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
