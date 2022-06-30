package com.training.MusicPlayer.entity;

import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.models.Genre;

import javax.persistence.*;

import java.util.*;

@Entity
@Table(name = "author")
public class AuthorSQL {
    @Id
    @Column(name = "author_id", nullable = false)
    private String author_id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SongSQL> songsSQL = new HashSet<>();


    public AuthorSQL() {
    }

    public void clone(Author author) {
        this.author_id = author.getId();
        this.name = author.getName();
    }

    public String getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(String author_id) {
        this.author_id = author_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<SongSQL> getSongsSQL() {
        return songsSQL;
    }

    public void setSongsSQL(Set<SongSQL> songsSQL) {
        this.songsSQL = songsSQL;
    }
}
