package com.training.MusicPlayer.models;

import com.cloudinary.Cloudinary;
import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;
import org.springframework.web.multipart.MultipartFile;

public class SongSourceUpload extends StoredFile {
    private String title;
    private MultipartFile file;
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
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}