package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.repositories.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private MongoTemplate mongoTemplate;
    public Optional<Author> getById(String id) {
        return repository.findById(id);
    }
    public List<Author> getAll() {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "name"));

        return mongoTemplate.find(query, Author.class, "author");
    }
    public List<Author> findByName(String name) {
        Query query = new Query();
        query.addCriteria(Criteria.where("name").regex(name,"i"));

        return mongoTemplate.find(query, Author.class, "author");
    }

    public Boolean checkAuthor(Author author) {
        List<Author> authors = findByName(author.getName());
        for (Author a : authors) {
            if (a.equals(author))
                return false;
        }
        return true;
    }
    public Author save(Author author) {
        repository.save(author);
        return author;
    }
    public String delete(String id) {
        if (getById(id).isPresent()) {
            repository.deleteById(id);
            return "OK";
        } else {
            return "NOT_FOUND";
        }
    }
    public Author update(Author author) {
        if (findByName(author.getName()).size() > 0) {
            return null;
        } else {
            repository.save(author);
            return author;
        }
    }
}
