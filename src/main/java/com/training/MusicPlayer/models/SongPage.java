package com.training.MusicPlayer.models;

import org.springframework.data.domain.Pageable;
import java.util.*;

public class SongPage {
    private List<Song> songs;
    private Pageable pageable;

    public SongPage(List<Song> songs, Pageable pageable) {
        this.songs = songs;
        this.pageable = pageable;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void setSongs(List<Song> songs) {
        this.songs = songs;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
