package com.training.MusicPlayer.models;

import com.training.MusicPlayer.entity.GenreSQL;
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

    public void clone(GenreSQL genreSQL) {
        this.id = genreSQL.getId();
        this.name = genreSQL.getName();
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

        return this.getName().equalsIgnoreCase(genreObj.getName());
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
