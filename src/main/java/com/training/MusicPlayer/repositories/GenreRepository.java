package com.training.MusicPlayer.repositories;

import com.training.MusicPlayer.models.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GenreRepository extends MongoRepository<Genre, String> {
}
