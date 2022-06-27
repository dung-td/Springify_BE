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

    @Override
    public int hashCode() {
        return this.getName().hashCode() + 32;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Genre genreObj = (Genre) obj;

        if (this.getId().equals(genreObj.getId()))
            return true;

        return this.getName().toLowerCase().equals(genreObj.getName().toLowerCase());
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
