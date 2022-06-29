package com.training.MusicPlayer.services.serviceimpl;

import com.training.MusicPlayer.entity.GenreSQL;
import com.training.MusicPlayer.models.Genre;
import com.training.MusicPlayer.repositories.sql.GenreSQLRepository;
import com.training.MusicPlayer.services.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreServiceSQLImpl implements GenreService {
    @Autowired
    private GenreSQLRepository repository;
    @Override
    public Optional<Genre> getById(String id) {
        Optional<GenreSQL> genreSQL = repository.findById(id);
        Genre genre = new Genre();

        genreSQL.ifPresent(genre::clone);

        return Optional.of(genre);
    }

    @Override
    public List<Genre> findByName(String name) {
        return null;
    }

    @Override
    public List<Genre> getAll() {
        return null;
    }

    @Override
    public Boolean checkGenre(Genre genre) {
        return null;
    }

    @Override
    public Genre save(Genre genre) {
        GenreSQL genreSQL = new GenreSQL();
        genreSQL.clone(genre);
        repository.save(genreSQL);
        return genre;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }
}
