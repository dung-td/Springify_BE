package com.training.MusicPlayer.services.serviceimpl;

import com.training.MusicPlayer.models.Genre;
import com.training.MusicPlayer.repositories.GenreRepository;
import com.training.MusicPlayer.services.GenreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GenreServiceMongoDBImpl implements GenreService {
    private static final Logger logger = LoggerFactory.getLogger(GenreServiceMongoDBImpl.class);
    @Autowired
    private GenreRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Optional<Genre> getById(String id) {
        return repository.findById(id);
    }
    @Override
    public List<Genre> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name,"i"));

        return mongoTemplate.find(query, Genre.class, "genre");
    }
    @Override
    public List<Genre> getAll() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "name"));

        mongoTemplate.find(query, Genre.class, "genre");

        return repository.findAll();
    }
    @Override
    public Boolean checkGenre(Genre genre) {
        List<Genre> genres = findByName(genre.getName());
        for (Genre g : genres) {
            if (g.equals(genre))
                return false;
        }
        return true;
    }
    @Override
    public Genre save(Genre genre) {
        repository.save(genre);
        return genre;
    }
    @Override
    public String delete(String id) {
        if (getById(id).isPresent()) {

            repository.deleteById(id);
            return "OK";
        } else {
            return "NOT_FOUND";
        }
    }
    @Override
    public Genre update(Genre genre) {
        if (findByName(genre.getName()).size() > 0) {
            return null;
        } else {
            repository.save(genre);
            return genre;
        }
    }

}
