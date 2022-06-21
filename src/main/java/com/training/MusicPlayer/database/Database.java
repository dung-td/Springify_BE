package com.training.MusicPlayer.database;

import com.training.MusicPlayer.dto.SongDto;
import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.services.AuthorService;
import com.training.MusicPlayer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class Database {
    private static final Logger logger = LoggerFactory.getLogger(Database.class);
    @Bean
    CommandLineRunner initDatabase(AuthorService service, SongService songService) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<Song> songs = songService.findAll();
                for (Song s :
                        songs) {
                    String authorString = s.getAuthor();
                    List<Author> authors = service.findByName(authorString);
                    if (authors.size() > 0) {
                        s.setAuthor(authors.get(0).getId());
                        songService.save(s);
                    }
                }

//                service.save(new Author("Hoàng Dũng"));
//                service.save(new Author("George Ezra"));
//                service.save(new Author("Trung Quân"));
//                service.save(new Author("Trinh"));
//                service.save(new Author("Blackpink"));
//                service.save(new Author("Mỹ Tâm"));
//                service.save(new Author("Rosé"));
//                service.save(new Author("Hà Thanh"));
//                service.save(new Author("Jax"));
//                service.save(new Author("Mỹ Linh"));
//                service.save(new Author("Mỹ Tâm"));
//                service.save(new Author("Hà Anh Tuấn"));
//                service.save(new Author("Đông Hùng"));
//                service.save(new Author("One Direction"));
//                service.save(new Author("Vũ."));
            }
        };
    }
}
