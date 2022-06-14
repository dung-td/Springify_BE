package com.training.MusicPlayer.controllers;

import com.training.MusicPlayer.models.ResponseObject;
import com.training.MusicPlayer.models.Song;
import com.training.MusicPlayer.repositories.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path = "/api/music")
public class SongController {
    @Autowired
    private SongRepository service;

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

//    @GetMapping(path = "page/")
//    ResponseEntity<ResponseObject> getSongPage(@RequestParam("page") long index, @RequestParam("limit") long limit) {
//        Optional<Song> song = service.findById(id);
//
//        if (song.isPresent()) {
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok", "Success", song)
//            );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ResponseObject("not-found", "Cannot find song with id: " + id, null)
//        );
//    }
}
