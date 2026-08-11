package com.satnam.CineGo.repository;

import com.satnam.CineGo.model.Movie;
import com.satnam.CineGo.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowRepository extends JpaRepository<Show, String> {

    List<Show> findAllByMovieId(String movieId);
}
