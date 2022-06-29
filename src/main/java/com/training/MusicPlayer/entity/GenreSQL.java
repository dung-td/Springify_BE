package com.training.MusicPlayer.entity;


import com.training.MusicPlayer.models.Genre;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "genre")
public class GenreSQL {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    public GenreSQL() {
    }

    public void clone(Genre genre) {
        this.id = genre.getId();
        this.name = genre.getName();
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
