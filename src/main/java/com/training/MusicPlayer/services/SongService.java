package com.training.MusicPlayer.services;

import com.cloudinary.utils.ObjectUtils;
import com.training.MusicPlayer.controllers.SongController;
import com.training.MusicPlayer.models.CloudinaryInstance;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongUpload;
import com.training.MusicPlayer.repositories.SongRepository;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class SongService {
    @Autowired
    private SongRepository repository;
    private static final Logger logger = LoggerFactory.getLogger(SongService.class);


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

    public List<Song> getPage(Integer index, Integer limit) {
        logger.info("Getting page: " + index + " and limit: " + limit);
        List<Song> songs = repository.findAll();
        int max_page = (int) Math.ceil((float) songs.size() / limit);

        if (index > max_page) {
          return null;
        } else {
            songs = songs.stream().skip((long) (index - 1) * limit).collect(Collectors.toList());
            if (limit > songs.size())
                limit = songs.size();

            songs = songs.subList(0, limit);

            return songs;
        }

    }

//    public String uploadSong(Song song, @ModelAttribute SongUpload songUpload) throws IOException {
//        Map uploadResult = null;
//        if (songUpload.getFile() != null && !songUpload.getFile().isEmpty()) {
//            uploadResult = instance.cloudinary.uploader().upload(songUpload.getFile().getBytes(),
//                    ObjectUtils.asMap("resource_type", "auto"));
//            songUpload.setPublicId((String) uploadResult.get("public_id"));
//            Object version = uploadResult.get("version");
//            if (version instanceof Integer) {
//                songUpload.setVersion(Long.valueOf(((Integer) version)));
//            } else {
//                songUpload.setVersion((Long) version);
//            }
//
//            songUpload.setSignature((String) uploadResult.get("signature"));
//            songUpload.setFormat((String) uploadResult.get("format"));
//            songUpload.setResourceType((String) uploadResult.get("resource_type"));
//        }
//
//        song.setUpload(songUpload);
//       // this.save(song);
//        return song.getSrc();
//    }

}

class SortSongByName implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getName().compareTo(o2.getName());
    }
}