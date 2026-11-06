package com.satnam.CineGo.controller;

import com.satnam.CineGo.dtos.requestDTOs.CreateMovieRequest;
import com.satnam.CineGo.dtos.responseDTOs.ErrorResponse;
import com.satnam.CineGo.dtos.responseDTOs.MovieSummaryResponse;

import java.time.LocalDateTime;
import java.util.List;

import com.satnam.CineGo.exception.MovieNotFoundException;
import com.satnam.CineGo.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;


    public MovieController(MovieService movieService) {

        this.movieService = movieService;
    }


    //addMovie -- handled by admin to add new movies --ADMIN ONLY
    @PostMapping("/addMovie")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MovieSummaryResponse> addNewMovie(@RequestBody CreateMovieRequest createMovieRequest) throws Exception {
        MovieSummaryResponse movieResponse = movieService.addNewMovie(createMovieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

    //ADMIN ONLY
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/movie/{movieId}")
    public ResponseEntity<String> deleteMovieById(@PathVariable String movieId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieService.deleteMovieById(movieId));
    }


    //home page to get list of movies in database
    @GetMapping("/")
    public ResponseEntity<List<MovieSummaryResponse>> getAllMovies() {

        List<MovieSummaryResponse> movies = movieService.getAllMovies();

        return ResponseEntity.ok(movies);
    }

    //    //movieId -- to get the details of particular movie+
    @GetMapping("/movie/id/{movieId}")
    public ResponseEntity<?> getMovieById(@PathVariable String movieId) {

        MovieSummaryResponse movieResponse = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movieResponse);

    }

    @GetMapping("/movie/name/{movieName}")
    public ResponseEntity<?> getMovieByName(@PathVariable String movieName) {

        return ResponseEntity.ok(movieService.getMovieByName(movieName));

    }
}
