package com.training.MusicPlayer.repositories.sql;

import com.training.MusicPlayer.entity.SongSQL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SongSQLRepository extends JpaRepository<SongSQL, String> {
}
