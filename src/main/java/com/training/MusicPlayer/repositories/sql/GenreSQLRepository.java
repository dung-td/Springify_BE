package com.training.MusicPlayer.repositories.sql;

import com.training.MusicPlayer.entity.GenreSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreSQLRepository extends JpaRepository<GenreSQL, String>  {
}
