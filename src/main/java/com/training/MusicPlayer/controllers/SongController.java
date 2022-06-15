package com.training.MusicPlayer.controllers;

import com.training.MusicPlayer.models.ResponseObject;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.repositories.SongRepository;
import com.training.MusicPlayer.services.SongService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
