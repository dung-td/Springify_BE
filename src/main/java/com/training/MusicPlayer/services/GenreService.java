package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreService {
    Optional<Genre> getById(String id);

    List<Genre> findByName(String name);

    List<Genre> getAll();

    Boolean checkGenre(Genre genre);

    Genre save(Genre genre);

    String delete(String id);

    Genre update(Genre genre);
}
