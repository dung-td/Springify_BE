package com.training.MusicPlayer.database;

import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongUpload;
import com.training.MusicPlayer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

@Configuration
public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase(SongService service) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                logger.info("Inserted:" + service.save(new Song("How you like that", "Blackpink", "Pop", "https://res.cloudinary.com/dungtd/video/upload/v1655261677/song/howyouliketthat_ncwsz3.mp3", new Date())));
//                logger.info("Inserted:" + service.save(new Song("Ice cream", "Blackpink", "Pop", "https://res.cloudinary.com/dungtd/video/upload/v1655261677/song/icecream_b2ute6.mp3", new Date())));
//                logger.info("Inserted:" + service.save(new Song("Gone", "Rosé", "Pop", "https://res.cloudinary.com/dungtd/video/upload/v1655261678/song/gone_pjqji8.mp3", new Date())));
//            }
//        };
//    }
}
