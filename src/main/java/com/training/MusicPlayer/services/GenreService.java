package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.Genre;
import com.training.MusicPlayer.repositories.GenreRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);
    @Autowired
    private GenreRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Optional<Genre> getById(String id) {
        return repository.findById(id);
    }
    public List<Genre> getAll() {
        return repository.findAll();
    }
    public String save(Genre genre) {
        repository.save(genre);
        return genre.getName();
    }
}
