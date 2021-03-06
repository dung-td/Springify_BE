package com.training.MusicPlayer.dto;

import com.training.MusicPlayer.entity.SongSQL;
import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.models.Genre;
import com.training.MusicPlayer.models.Song;

import java.util.Date;

public class SongDto {
    private String id;
    private String name;
    private Author author;
    private Genre genre;
    private String src;
    private String thumbnail;
    private Date updateAt;
    private Long streams;

    public SongDto() {
    }

    public void clone(Song song) {
        this.id = song.getId();
        this.name = song.getName();
        this.src = song.getSrc();
        this.thumbnail = song.getThumbnail();
        this.updateAt = song.getUpdateAt();
        this.streams = song.getStreams();
    }

    public void cloneSQL(SongSQL song) {
        this.id = song.getId();
        this.name = song.getName();
        this.src = song.getSrc();
        this.thumbnail = song.getThumbnail();
        this.updateAt = song.getDate();
        this.streams = Long.valueOf(song.getStream());
        this.author = new Author();
        this.genre = new Genre();
        this.author.clone(song.getAuthor());
        this.genre.clone(song.getGenre());
    }

    public SongDto(String id, String name, Author author, Genre genre, String src, String thumbnail, Date updateAt, Long streams) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.src = src;
        this.thumbnail = thumbnail;
        this.updateAt = updateAt;
        this.streams = streams;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Long getStreams() {
        return streams;
    }

    public void setStreams(Long streams) {
        this.streams = streams;
    }
}
