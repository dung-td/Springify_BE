package com.training.MusicPlayer.services.serviceimpl;

import com.training.MusicPlayer.entity.AuthorSQL;
import com.training.MusicPlayer.models.Author;
import com.training.MusicPlayer.repositories.sql.AuthorSQLRepository;
import com.training.MusicPlayer.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceSQLImpl implements AuthorService {
    @Autowired
    private AuthorSQLRepository repository;
    @Override
    public Optional<Author> getById(String id) {
        return Optional.empty();
    }

    @Override
    public List<Author> getAll() {
        List<AuthorSQL> authorsSQL = repository.findAll();
        List<Author> authors = new ArrayList<>();
        for (AuthorSQL authorSQL:
                authorsSQL) {
            Author author = new Author();
//            author.clone(author);
        }
        return null;
    }

    @Override
    public List<Author> findByName(String name) {
        return null;
    }

    @Override
    public Boolean checkAuthor(Author author) {
        return null;
    }

    @Override
    public Author save(Author author) {
        AuthorSQL authorSQL = new AuthorSQL();
        authorSQL.clone(author);
        repository.save(authorSQL);
        return null;
    }

    @Override
    public String delete(String id) {
        return null;
    }

    @Override
    public Author update(Author author) {
        return null;
    }
}
