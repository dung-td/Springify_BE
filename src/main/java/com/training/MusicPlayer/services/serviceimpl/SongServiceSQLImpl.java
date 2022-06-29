package com.training.MusicPlayer.services.serviceimpl;

import com.training.MusicPlayer.dto.SongDto;
import com.training.MusicPlayer.entity.SongSQL;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongPage;
import com.training.MusicPlayer.repositories.sql.SongSQLRepository;
import com.training.MusicPlayer.services.SongService;
import com.training.MusicPlayer.utils.SongSourceUpload;
import com.training.MusicPlayer.utils.SongThumbnailUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class SongServiceSQLImpl implements SongService {
    @Autowired
    private SongSQLRepository repository;

    @Override
    public List<SongDto> findAllDto(Boolean shuffle) {
        Query query = new Query();
        query.with(Sort.by(Sort.Direction.ASC, "updateAt"));

        List<SongSQL> songs = repository.findAll();
        List<SongDto> songsDto = new ArrayList<>();
        for (SongSQL s:
                songs) {
            SongDto songDto = new SongDto();
            songDto.cloneSQL(s);

//            Optional<Genre> genre = genreService.getById(s.getGenre());
//            Optional<Author> author = authorService.getById(s.getAuthor());
//
//            genre.ifPresent(songDto::setGenre);
//            author.ifPresent(songDto::setAuthor);
            songsDto.add(songDto);
        }

        if (shuffle) {
            Collections.shuffle(songsDto);
            return songsDto;
        }

        return songsDto;
    }

    @Override
    public List<Song> findAll() {
        return null;
    }

    @Override
    public long count(String name, String author, String genre) {
        return 0;
    }

    @Override
    public SongDto findById(String id) {
        SongDto songDto = new SongDto();
        Optional<SongSQL> song = repository.findById(id);
        if (song.isPresent()) {
            songDto.cloneSQL(song.get());

//            Optional<Genre> genre = genreService.getById(song.get().getGenre());
//            Optional<Author> author = authorService.getById(song.get().getAuthor());
//
//            genre.ifPresent(songDto::setGenre);
//            author.ifPresent(songDto::setAuthor);

            return songDto;
        } else {
            return null;
        }
    }

    @Override
    public Boolean checkSong(Song song) {
        return null;
    }

    @Override
    public String save(Song song) {
        return null;
    }

    public String saveSQL(SongSQL song) {
        repository.save(song);
        return song.getName();
    }

    @Override
    public SongPage getPage(String name, String author, String genre, int index, Integer limit, Pageable pageable) {
        return null;
    }

    @Override
    public Song editSong(Song song) {
        return null;
    }

    @Override
    public String delete(Song song) throws IOException {
        return null;
    }

    @Override
    public List<SongDto> getRelatedSong(String songId) {
        return null;
    }

    @Override
    public Song uploadSongSource(Song song, SongSourceUpload songUpload) throws IOException {
        return null;
    }

    @Override
    public Song uploadSongThumbnail(Song song, SongThumbnailUpload songThumbnailUpload) throws IOException {
        return null;
    }

    @Override
    public SongDto updateStream(SongDto song) {
        return null;
    }
}
