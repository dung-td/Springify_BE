package com.training.MusicPlayer.models;

import com.cloudinary.StoredFile;
import com.cloudinary.Transformation;
import org.springframework.web.multipart.MultipartFile;

public class SongUpload extends StoredFile {
    private String title;
    private MultipartFile file;
    private CloudinaryInstance instance;

    public String getUrl() {
        if (version != null && format != null && publicId != null) {
            return instance.cloudinary.url()
                    .resourceType(resourceType)
                    .type(type)
                    .format(format)
                    .version(version)
                    .generate(publicId);
        } else return null;
    }

    public String getThumbnailUrl() {
        if (version != null && format != null && publicId != null) {
            return instance.cloudinary.url().format(format)
                    .resourceType(resourceType)
                    .type(type)
                    .version(version).transformation(new Transformation().width(150).height(150).crop("fit"))
                    .generate(publicId);
        } else return null;
    }

    public String getComputedSignature() {
        return getComputedSignature(instance.cloudinary);
    }

    public boolean validSignature() {
        return getComputedSignature().equals(signature);
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