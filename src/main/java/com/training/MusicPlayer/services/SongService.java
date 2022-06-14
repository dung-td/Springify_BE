package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SongService {
    @Autowired
    private SongRepository repository;

    public List<Song> findAll() {
        return repository.findAll();
    }
    public Optional<Song> findById(Long id) {
        return repository.findById(id);
    }

    public String save(Song song) {
        repository.save(song);
        return song.getName();
    }

}
