package com.training.MusicPlayer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication()
@EnableMongoRepositories

public class MusicPlayerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicPlayerApplication.class, args);
    }
}
