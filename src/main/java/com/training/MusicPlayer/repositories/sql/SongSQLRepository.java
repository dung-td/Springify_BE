package com.training.MusicPlayer.repositories.sql;

import com.training.MusicPlayer.entity.SongSQL;
import com.training.MusicPlayer.models.SongPage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface SongSQLRepository extends JpaRepository<SongSQL, String> {

    List<SongSQL> findAllByNameContainingAndAuthorContainingAndGenreContaining(String name, String author, String genre);

    long countByNameContainingAndAuthorContainingAndGenreContaining(String name, String author, String genre);
}
