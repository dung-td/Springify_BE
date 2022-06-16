package com.training.MusicPlayer.models;

import com.cloudinary.Cloudinary;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;
import org.springframework.web.multipart.MultipartFile;

public class SongUpload extends StoredFile {
    private String title;
    private MultipartFile src;

    private MultipartFile thumbnail;
    public String getUrl(Cloudinary instance) {
        if (version != null && format != null && publicId != null) {
            return instance.url()
                    .resourceType(resourceType)
                    .type(type)
                    .format(format)
                    .version(version)
                    .generate(publicId);
        } else return null;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getFile() {
        return src;
    }

    public void setFile(MultipartFile file) {
        this.src = file;
    }

    public MultipartFile getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(MultipartFile thumbnail) {
        this.thumbnail = thumbnail;
    }
}