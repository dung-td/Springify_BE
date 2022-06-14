package com.training.MusicPlayer.database;

import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.repositories.SongRepository;
import com.training.MusicPlayer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(SongService service) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                logger.info("Inserted: " + service.save(new Song("How you like that", "Pop", new Date())));
                logger.info("Inserted: " + service.save(new Song("How you like that", "Pop", new Date())));
                logger.info("Inserted: " + service.save(new Song("How you like that", "Pop", new Date())));
                logger.info("Inserted: " + service.save(new Song("How you like that", "Pop", new Date())));
            }
        };
    }
}
