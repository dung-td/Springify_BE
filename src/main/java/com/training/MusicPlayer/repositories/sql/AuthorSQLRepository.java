package com.training.MusicPlayer.repositories.sql;

import com.training.MusicPlayer.entity.AuthorSQL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorSQLRepository extends JpaRepository<AuthorSQL, String> {
}
