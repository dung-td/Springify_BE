package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    Optional<Author> getById(String id);

    List<Author> getAll();

    List<Author> findByName(String name);

    Boolean checkAuthor(Author author);

    Author save(Author author);

    String delete(String id);

    Author update(Author author);
}
