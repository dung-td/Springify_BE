package com.training.MusicPlayer.entity;


import com.training.MusicPlayer.models.Genre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genre")
public class GenreSQL {
    @Id
    @Column(name = "genre_id", nullable = false)
    private String genre_id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "genre", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<SongSQL> songsSQL = new HashSet<>();

    public GenreSQL() {
    }

    public void clone(Genre genre) {
        this.genre_id = genre.getId();
        this.name = genre.getName();
    }

    public String getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(String genre_id) {
        this.genre_id = genre_id;
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
