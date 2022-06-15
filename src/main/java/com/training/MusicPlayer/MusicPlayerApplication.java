package com.training.MusicPlayer;

import com.cloudinary.utils.ObjectUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.cloudinary.*;

@SpringBootApplication()
@EnableMongoRepositories

public class MusicPlayerApplication {

    Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dungtd",
            "api_key", "553685374214836",
            "api_secret", "QLOlTiPPPESG9iyQhzG634GfhBQ"));

    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerApplication.class, args);
    }
}
