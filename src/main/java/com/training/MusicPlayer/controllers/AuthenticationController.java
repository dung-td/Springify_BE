package com.training.MusicPlayer.controllers;


import com.training.MusicPlayer.models.CustomUserDetails;
import com.training.MusicPlayer.models.JwtResponse;
import com.training.MusicPlayer.models.ResponseObject;
import com.training.MusicPlayer.models.User;
import com.training.MusicPlayer.utils.JwtToken;
import org.cloudinary.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthenticationController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken token;

    private static final Logger logger = LoggerFactory.getLogger(SongController.class);

    @PostMapping("/login")
    public ResponseEntity<ResponseObject> authenticateUser(@RequestBody User user) {

        logger.info("Loging in:..." + user.getUsername() + "/" + user.getPassword());

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword()
                    )
            );

            logger.info("Correct login information!");
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = token.generateToken((CustomUserDetails) authentication.getPrincipal());

            logger.info("Generated token: " + jwt);

            JwtResponse token = new JwtResponse(jwt);

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("success", "Login success", token)
            );

        } catch (AuthenticationException exception) {
            logger.error("Error: "+ exception.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("error", "error", null)
        );
    }
}
