package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.repositories.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(GenreService.class);
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Optional<Author> getById(String id) {
        return repository.findById(id);
    }
    public List<Author> getAll() {
        return repository.findAll();
    }
    public List<Author> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name,"i"));

        return mongoTemplate.find(query, Author.class, "author");
    }
    public String save(Author author) {
        if (findByName(author.getName()).size() > 0) {
            return "error";
        } else {
            repository.save(author);
            return author.getName();
        }
    }
}
