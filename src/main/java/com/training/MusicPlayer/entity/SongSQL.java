package com.training.MusicPlayer.entity;


import com.training.MusicPlayer.dto.SongDto;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "song")
public class SongSQL {
    @Id
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "author", nullable = false)
    private String author;

    @Column(name = "genre", nullable = false)
    private String genre;

    @Column(name = "updateAt", nullable = false)
    private Date date;

    @Column(name = "src", nullable = false)
    private String src;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "stream", nullable = false)
    private Integer stream;

    public void clone(SongDto songDto) {
        this.id = songDto.getId();
        this.name = songDto.getName();
        this.author = songDto.getAuthor().getId();
        this.genre = songDto.getGenre().getId();
        this.src = songDto.getSrc();
        this.thumbnail = songDto.getThumbnail();
        this.date = songDto.getUpdateAt();
        this.stream = Math.toIntExact(songDto.getStreams());
    }

    public SongSQL() {
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

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public Integer getStream() {
        return stream;
    }

    public void setStream(Integer stream) {
        this.stream = stream;
    }
}
