package com.training.MusicPlayer.models;

import javax.persistence.Id;
import java.util.Date;

public class SongDto {
    private String id;
    private String name;
    private String author;
    private Genre genre;
    private String src;
    private String thumbnail;
    private Date updateAt;

    public SongDto() {
    }

    public void clone(Song song) {
        this.id = song.getId();
        this.author = song.getAuthor();
        this.name = song.getName();
        this.src = song.getSrc();
        this.thumbnail = song.getThumbnail();
        this.updateAt = song.getUpdateAt();
    }

    public SongDto(String id, String name, String author, Genre genre, String src, String thumbnail, Date updateAt) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.src = src;
        this.thumbnail = thumbnail;
        this.updateAt = updateAt;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
}
