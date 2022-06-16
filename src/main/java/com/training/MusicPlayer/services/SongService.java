package com.training.MusicPlayer.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongUpload;
import com.training.MusicPlayer.repositories.SongRepository;

import java.io.IOException;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.*;

@Service
public class SongService {
    @Autowired
    private SongRepository repository;

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dungtd",
            "api_key", "553685374214836",
            "api_secret", "QLOlTiPPPESG9iyQhzG634GfhBQ"));
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

    public Song editSong(Song song) {
        logger.info("Editing song: " + song.getName());
        Optional<Song> songToUpdate = repository.findById(song.getId());

        if (songToUpdate.isPresent()) {
            songToUpdate.get().updateData(song);
            repository.save(songToUpdate.get());
            return songToUpdate.get();
        } else {
            return null;
        }

    }

    public String delete(Song song) throws IOException {
        logger.info("Deleting song: " + song.getName());
        Optional<Song> songToDelete = repository.findById(song.getId());

        Map deleteResult = null;

        if (songToDelete.get().getSrcId() != null) {
            deleteResult = cloudinary.uploader().destroy(songToDelete.get().getSrcId(),  ObjectUtils.asMap("resource_type", "video"));

            logger.info(deleteResult.toString());
        }

        if (songToDelete.isPresent()) {
            repository.delete(songToDelete.get());
            return "success";
        } else {
            return "error";
        }

    }

    public Song uploadSong(Song song, @ModelAttribute SongUpload songUpload) throws IOException {
        Map uploadResult = null;

        if (songUpload.getFile() != null && !songUpload.getFile().isEmpty()) {
            uploadResult = cloudinary.uploader().upload(songUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto", "folder", "/song"));
            songUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");

            logger.info("Upload source success: " + uploadResult);

            if (version instanceof Integer) {
                songUpload.setVersion(Long.valueOf(((Integer) version)));
            } else {
                songUpload.setVersion((Long) version);
            }

            songUpload.setSignature((String) uploadResult.get("signature"));
            songUpload.setFormat((String) uploadResult.get("format"));
            songUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        if (songUpload.getFile() != null && !songUpload.getFile().isEmpty()) {
            uploadResult = cloudinary.uploader().upload(songUpload.getFile().getBytes(),
                    ObjectUtils.asMap("resource_type", "auto", "folder", "/song"));
            songUpload.setPublicId((String) uploadResult.get("public_id"));
            Object version = uploadResult.get("version");

            logger.info("Upload source success: " + uploadResult);

            if (version instanceof Integer) {
                songUpload.setVersion(Long.valueOf(((Integer) version)));
            } else {
                songUpload.setVersion((Long) version);
            }

            songUpload.setSignature((String) uploadResult.get("signature"));
            songUpload.setFormat((String) uploadResult.get("format"));
            songUpload.setResourceType((String) uploadResult.get("resource_type"));
        }

        String songSrcUrl = songUpload.getUrl(cloudinary);
        song.setSrc(songSrcUrl);
        song.setSrcId(songUpload.getPublicId());

        this.save(song);

        return song;
    }

}

class SortSongByName implements Comparator<Song> {
    @Override
    public int compare(Song o1, Song o2) {
        return o1.getName().compareTo(o2.getName());
    }
}