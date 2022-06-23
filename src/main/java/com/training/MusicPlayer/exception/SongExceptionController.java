package com.training.MusicPlayer.exception;

import com.training.MusicPlayer.response.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SongExceptionController {
    @ExceptionHandler(value = SongNotFoundException.class)
    public ResponseEntity<ResponseObject> exception (SongNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("NOT_FOUND", "Song not found", null));
    }
}
