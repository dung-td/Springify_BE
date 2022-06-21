package com.training.MusicPlayer.models;

import com.training.MusicPlayer.dto.SongDto;
import org.springframework.data.domain.Pageable;
import java.util.*;

public class SongPage {
    private List<SongDto> songs;
    private Pageable pageable;

    public SongPage(List<SongDto> songs, Pageable pageable) {
        this.songs = songs;
        this.pageable = pageable;
    }

    public List<SongDto> getSongs() {
        return songs;
    }

    public void setSongs(List<SongDto> songs) {
        this.songs = songs;
    }

    public Pageable getPageable() {
        return pageable;
    }

    public void setPageable(Pageable pageable) {
        this.pageable = pageable;
    }
}
