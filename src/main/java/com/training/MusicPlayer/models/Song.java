package com.training.MusicPlayer.models;

import org.springframework.data.mongodb.core.mapping.Document;

import com.cloudinary.StoredFile;
import javax.persistence.Id;
import java.util.Date;

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

    public Song() {
    }

    public Song(String name, String author, String genre, Date updateAt) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.updateAt = updateAt;
    }

    public Song(String name, String author, String genre, String src, Date updateAt) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.src = src;
        this.updateAt = updateAt;
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
    }

    @Override
    public String toString() {
        return "Song{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", src='" + src + '\'' +
                ", updateAt=" + updateAt +
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

    public StoredFile getUpload() {
        StoredFile file = new StoredFile();
        file.setPreloadedFile(src);
        return file;
    }

    public void setUpload(StoredFile file) {
        this.src = file.getPreloadedFile();
    }
}
