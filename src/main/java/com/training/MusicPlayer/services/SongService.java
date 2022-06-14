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

    public Optional<Song> findById(String id) {
        return repository.findById(id);
    }

    public String save(Song song) {
        repository.save(song);
        return song.getName();
    }
}

class SortSongByName implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
