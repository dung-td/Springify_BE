package com.training.MusicPlayer.database;

import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.User;
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
import java.util.Date;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase(UserService user) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                String password = BCrypt.hashpw("123456", BCrypt.gensalt());
//                logger.info("Inserted:" + user.save(new User("admin", password)));
//            }
//        };
//    }
}
