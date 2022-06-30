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

    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)
    private AuthorSQL author;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private GenreSQL genre;

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
        this.author.clone(songDto.getAuthor());
        this.genre.clone(songDto.getGenre());
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

    public AuthorSQL getAuthor() {
        return author;
    }

    public void setAuthor(AuthorSQL author) {
        this.author = author;
    }

    public GenreSQL getGenre() {
        return genre;
    }

    public void setGenre(GenreSQL genre) {
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
