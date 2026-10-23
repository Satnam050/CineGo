package com.satnam.CineGo.controller;

import com.satnam.CineGo.dtos.requestDTOs.CreateMovieRequest;
import com.satnam.CineGo.dtos.responseDTOs.MovieSummaryResponse;

import java.util.List;

import com.satnam.CineGo.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private MovieService movieService;


    public MovieController(MovieService movieService){
        this.movieService=movieService;
    }

    /*
    TODO:
            3. /shows/movieId -- to get the shows of given movieId


     */


     //addMovie -- handled by admin to add new movies --ADMIN ONLY
    @PostMapping("/addMovie")
    public ResponseEntity<MovieSummaryResponse> addNewMovie(@RequestBody CreateMovieRequest createMovieRequest) throws Exception {
        MovieSummaryResponse movieResponse = movieService.addNewMovie(createMovieRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieResponse);
    }

//    //TODO /update: update movie details
//
//
//    //ADMIN ONLY
//    @DeleteMapping("/movie")
//    public ResponseEntity<String> deleteMovieById(@RequestHeader(name = "X-MOVIE_ID") String movieId){
//        return ResponseEntity.status(HttpStatus.ACCEPTED).body(movieService.deleteMovieById(movieId));
//    }
//


    //home page to get list of movies in database
    @GetMapping("/")
    public ResponseEntity<List<MovieSummaryResponse>> getAllMovies(){

        List<MovieSummaryResponse>movies = movieService.getAllMovies();

        return ResponseEntity.ok(movies);
    }

//    //movieId -- to get the details of particular movie
    @GetMapping("/movie/{movieId}")
    public ResponseEntity<MovieSummaryResponse> getMovieById(@PathVariable String movieId){
        MovieSummaryResponse movieResponse = movieService.getMovieById(movieId);
        return ResponseEntity.ok(movieResponse);
    }
}
