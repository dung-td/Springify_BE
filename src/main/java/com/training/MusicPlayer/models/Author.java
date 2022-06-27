package com.training.MusicPlayer.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Author {
    private String id;
    private String name;
    public Author() {
    }
    public Author(String name) {
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

        Author authorObj = (Author) obj;

        if (this.getId().equals(authorObj.getId()))
            return true;

        return this.getName().toLowerCase().equals(authorObj.getName().toLowerCase());
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
