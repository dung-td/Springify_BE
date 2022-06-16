package com.training.MusicPlayer.controllers;

import com.training.MusicPlayer.models.ResponseObject;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.models.SongUpload;
import com.training.MusicPlayer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    ResponseEntity<ResponseObject> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Success", service.findAll())
        );
    }

    @GetMapping(path = "")
    ResponseEntity<ResponseObject> getById(@RequestParam("id") String id) {
        Optional<Song> song = service.findById(id);

        if (song.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", song)
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("not-found", "Cannot find song with id: " + id, null)
        );
    }

    @GetMapping(path = "/page")
    ResponseEntity<ResponseObject> getSongPage(@RequestParam("page") Integer index, @RequestParam("limit") Integer limit) {
        if (index == null)
            index = 0;
        if (limit == null)
            limit = 4;

        List<Song> song = service.getPage(index, limit);

        if (song != null) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", song)
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
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("not-found", "Cannot update song with id: " + s.getId(), null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }
    }

    @DeleteMapping(value = "/delete")
    ResponseEntity<ResponseObject> deleteSong(@RequestParam("id") String id) {
        if (id != null) {
            Song song = new Song();
            song.setId(id);
            String status = "";
            try {
                status = service.delete(song);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            if (status.equals("error")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("not-found", "Cannot find song with id: " + song.getId(), null)
                );
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Success", null)
                );
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(
                    new ResponseObject("error", "Missing required ID", null)
            );
        }


    }

    @PostMapping(value = "/upload")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam MultipartFile src, @RequestParam String name, @RequestParam String author, @RequestParam String genre) {
        logger.info(String.format("File name '%s' with name '%s' author '%s' genre '%s' uploaded successfully.", src.getOriginalFilename(), name, author, genre));

        SongUpload songUpload = new SongUpload();
        songUpload.setFile(src);
        songUpload.setTitle(name);

        Song song = new Song(name, author, genre, new Date());

        try {
            Song afterUpload = service.uploadSong(song, songUpload);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Success", afterUpload)
            );
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("error", "Error while upload file", null)
            );
        }
    }
}
