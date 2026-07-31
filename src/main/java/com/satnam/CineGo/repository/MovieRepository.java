package com.satnam.CineGo.repository;

import com.satnam.CineGo.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface MovieRepository extends JpaRepository<Movie, String> {
    Movie findByNameAndReleaseDate(String name, Date releaseDate);
}
