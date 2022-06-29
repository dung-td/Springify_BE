package com.training.MusicPlayer.database;

import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.models.Genre;
import com.training.MusicPlayer.services.serviceimpl.AuthorServiceMongoDBImpl;
import com.training.MusicPlayer.services.serviceimpl.AuthorServiceSQLImpl;
import com.training.MusicPlayer.services.serviceimpl.GenreServiceMongoDBImpl;
import com.training.MusicPlayer.services.serviceimpl.GenreServiceSQLImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.*;

@Configuration
public class Database {
//    private static final Logger logger = LoggerFactory.getLogger(Database.class);
//    @Bean
//    CommandLineRunner initDatabase(AuthorServiceMongoDBImpl authorMongoDBService, AuthorServiceSQLImpl authorSQLService) {
//        return new CommandLineRunner() {
//            @Override
//            public void run(String... args) throws Exception {
//                List<Author> authors = authorMongoDBService.getAll();
//
//                for (Author a:
//                     authors) {
//                    logger.info("Copying..." + a.getId());
//                    authorSQLService.save(a);
//                }
//            }
//        };
//    }
}
