package com.training.MusicPlayer.controllers;

import com.training.MusicPlayer.dto.SongDto;
import com.training.MusicPlayer.exception.SongNotFoundException;
import com.training.MusicPlayer.models.*;
import com.training.MusicPlayer.response.ResponseObject;
import com.training.MusicPlayer.services.SongService;
import com.training.MusicPlayer.utils.SongSourceUpload;
import com.training.MusicPlayer.utils.SongThumbnailUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping(path = "/api/music")
public class SongController {
    @Autowired
    private SongService service;
    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    @GetMapping(path = "/all")
    ResponseEntity<ResponseObject> getAll(@RequestParam(required = false, value = "shuffle") Boolean shuffle) {
        logger.info("Getting song list:...");

        if (shuffle == null) {
            shuffle = false;
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", service.findAllDto(shuffle))
        );
    }

    @GetMapping(path = "/count")
    ResponseEntity<ResponseObject> count(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "author") String author,
            @RequestParam(required = false, value = "genre") String genre) {
        logger.info("Counting songs:...");

        if (name == null)
            name = "";
        if (author == null)
            author = "";
        if (genre == null)
            genre = "";

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("OK", "Success", service.count(name, author, genre))
        );
    }

    @GetMapping(path = "/get")
    ResponseEntity<ResponseObject> getById(@RequestParam("id") String id) {
        SongDto song = service.findById(id);

        if (song != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", song)
            );
        } else {
            throw new SongNotFoundException();
        }
    }

    @GetMapping(path = "/page")
    ResponseEntity<ResponseObject> getSongPage(
            @RequestParam(required = false, value = "name") String name,
            @RequestParam(required = false, value = "author") String author,
            @RequestParam(required = false, value = "genre") String genre,
            @RequestParam("page") Integer index,
            @RequestParam("limit") Integer limit) {

        if (index == null)
            index = 0;
        if (limit == null)
            limit = 4;
        if (name == null)
            name = "";
        if (author == null)
            author = "";
        if (genre == null)
            genre = "";

        Pageable pageable = PageRequest.of(index , limit);

        SongPage page = service.getPage(name, author, genre, index, limit, pageable);

        if (page != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", page)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("not-found", "Cannot find song with page: " + index, null)
        );
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    ResponseEntity<ResponseObject> updateSong(@RequestBody Song s) {
        if (s.getId() != null) {
            Song afterEdit = service.editSong(s);

            if (afterEdit != null) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Success", afterEdit)
                );
            } else {
                throw new SongNotFoundException();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }
    }

    @DeleteMapping(value = "/delete")
    ResponseEntity<ResponseObject> deleteSong(@RequestParam(name = "id") List<String> list) {
        if (list.size() > 0) {
            Song song = new Song();
            for (String s : list) {
                song.setId(s);
                String status = "";

                try {
                    status = service.delete(song);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                if (status == null) {
                    throw new SongNotFoundException();
                }
            }
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", null)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }
    }

    @PostMapping(value = "/upload")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam MultipartFile src, @RequestParam(required = false) MultipartFile thumbnail, @RequestParam String name, @RequestParam String author, @RequestParam String genre) {
        logger.info(String.format("File name '%s' with name '%s' author '%s' genre '%s' uploaded successfully.", src.getOriginalFilename(), name, author, genre));

        SongSourceUpload songSourceUpload = new SongSourceUpload();
        songSourceUpload.setFile(src);
        songSourceUpload.setTitle(name);

        SongThumbnailUpload songThumbnailUpload = new SongThumbnailUpload();
        songThumbnailUpload.setFile(thumbnail);
        songThumbnailUpload.setTitle(name);

        Song song = new Song(name, author, genre, new Date());

        try {
            Song afterUpload = service.uploadSongSource(song, songSourceUpload);
            if (songThumbnailUpload.getFile() != null) {
                afterUpload = service.uploadSongThumbnail(song, songThumbnailUpload);
            }

            logger.info("Saving...:" + service.save(afterUpload)) ;

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", afterUpload)
            );

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("error", "Error while upload file", null)
            );
        }
    }

    @GetMapping(value = "/getRelated")
    ResponseEntity<ResponseObject> getAllSongs(@RequestParam("id") String id) {

        List<SongDto> relatedList = service.getRelatedSong(id);
        if (relatedList == null)
            throw new SongNotFoundException();

        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "success", service.getRelatedSong(id))
        );
    }




}
