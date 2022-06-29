package com.training.MusicPlayer.models;

import com.training.MusicPlayer.dto.SongDto;
import org.springframework.data.mongodb.core.mapping.Document;

import com.cloudinary.StoredFile;
import javax.persistence.Id;
import java.util.Date;
import java.util.Locale;

@Document
public class Song {
    @Id
    private String id;
    private String name;
    private String author;
    private String genre;
    private String src;
    private String srcId;
    private String thumbnail;
    private String thumbnailId;
    private Date updateAt;
    private Long streams;

    public Song() {
    }

    public void clone(SongDto songDto) {
        this.id = songDto.getId();
        this.name = songDto.getName();
        this.author = songDto.getAuthor().getId();
        this.genre = songDto.getGenre().getId();
        this.src = songDto.getSrc();
        this.thumbnail = songDto.getThumbnail();
        this.updateAt = songDto.getUpdateAt();
        this.streams = songDto.getStreams();
    }

    public Song(String name, String author, String genre, Date updateAt, Long streams) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.updateAt = updateAt;
        this.streams = streams;
    }

    public Song(String name, String author, String genre, String src, Date updateAt, Long streams) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.src = src;
        this.updateAt = updateAt;
        this.streams = streams;
    }

    public void updateData(Song s) {
        if (s.getName() != null)
            this.name = s.getName();
        if (s.getAuthor() != null)
            this.author = s.getAuthor();
        if (s.getSrc() != null)
            this.src = s.getSrc();
        if (s.getGenre() != null)
            this.genre = s.getGenre();
        if (s.getStreams() != null) {
            this.streams = s.getStreams();
        }
    }

    @Override
    public int hashCode() {
        return this.getName().hashCode() + this.getAuthor().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Song songObj = (Song) obj;

        if (this.getId().equals(songObj.getId()))
            return true;

        return this.getAuthor().equalsIgnoreCase(songObj.getAuthor())
                && this.getName().equalsIgnoreCase(songObj.getName());
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", src='" + src + '\'' +
                ", srcId='" + srcId + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", thumbnailId='" + thumbnailId + '\'' +
                ", updateAt=" + updateAt +
                ", streams=" + streams +
                '}';
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

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSrcId() {
        return srcId;
    }

    public void setSrcId(String srcId) {
        this.srcId = srcId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getThumbnailId() {
        return thumbnailId;
    }

    public void setThumbnailId(String thumbnailId) {
        this.thumbnailId = thumbnailId;
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

    public StoredFile getUpload() {
        StoredFile file = new StoredFile();
        file.setPreloadedFile(src);
        return file;
    }

    public void setUpload(StoredFile file) {
        this.src = file.getPreloadedFile();
    }
}
