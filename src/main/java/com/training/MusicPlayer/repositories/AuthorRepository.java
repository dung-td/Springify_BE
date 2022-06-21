package com.training.MusicPlayer.repositories;

import com.training.MusicPlayer.models.Author;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorRepository extends MongoRepository<Author, String> {
}
