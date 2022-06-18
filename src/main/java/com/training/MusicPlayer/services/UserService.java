package com.training.MusicPlayer.services;

import com.training.MusicPlayer.models.CustomUserDetails;
import com.training.MusicPlayer.models.User;
import com.training.MusicPlayer.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository repository;
    @Autowired
    MongoTemplate mongoTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Query query = new Query();

        query.addCriteria(Criteria.where("username").is(username));
        List<User> users = mongoTemplate.find(query, User.class, "user");

        if (users.size() > 0) {
            return new CustomUserDetails(users.get(0));
        } else {
            return null;
        }
    }

    public UserDetails loadUserById(String id) throws UsernameNotFoundException {
        User user = mongoTemplate.findById(id, User.class, "user");

        if (user != null) {
            return new CustomUserDetails(user);
        } else {
            return null;
        }
    }

    public String save(User user) {
        repository.save(user);
        return user.getUsername();
    }
}
