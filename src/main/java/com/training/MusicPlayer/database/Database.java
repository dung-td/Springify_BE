package com.training.MusicPlayer.database;

import com.training.MusicPlayer.models.Genre;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.User;
import com.training.MusicPlayer.services.GenreService;
import com.training.MusicPlayer.services.SongService;
import com.training.MusicPlayer.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase(GenreService genreService, SongService songService) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                List<Song> songs = songService.findAll();
//
//                for (Song s: songs) {
//                    switch (s.getGenre()) {
//                        case "K-Pop" -> s.setGenre("62b0419cc3479c37f7871b2f");
//                        case "US-UK" -> s.setGenre("62b0419cc3479c37f7871b30");
//                        case "1900 hồi đó" -> s.setGenre("62b0419cc3479c37f7871b32");
//                        case "Nhạc trẻ" -> s.setGenre("62b0419cc3479c37f7871b31");
//                    }
//                    songService.editSong(s);
//                }
//            }
//        };
//    }
}
