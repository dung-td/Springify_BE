package com.training.MusicPlayer.services;

import com.training.MusicPlayer.dto.SongDto;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongPage;
import com.training.MusicPlayer.utils.SongSourceUpload;
import com.training.MusicPlayer.utils.SongThumbnailUpload;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;

public interface SongService {

    List<SongDto> findAllDto(Boolean shuffle);

    List<Song> findAll();

    long count(String name, String author, String genre);

    SongDto findById(String id);

    Boolean checkSong(Song song);

    String save(Song song);

    SongPage getPage(String name, String author, String genre, int index, Integer limit, Pageable pageable);

    Song editSong(Song song);

    String delete(Song song) throws IOException;

    List<SongDto> getRelatedSong(String songId);

    Song uploadSongSource(Song song, @ModelAttribute SongSourceUpload songUpload) throws IOException;

    Song uploadSongThumbnail(Song song, @ModelAttribute SongThumbnailUpload songThumbnailUpload) throws IOException;

    SongDto updateStream(SongDto song);
}
