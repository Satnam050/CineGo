package com.satnam.CineGo.services;

import com.satnam.CineGo.dtos.CreateMovieRequest;
import com.satnam.CineGo.model.Movie;
import com.satnam.CineGo.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;



    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
       return movieRepository.findAll();
    }

    public Movie addNewMovie(CreateMovieRequest createMovieRequest) throws Exception {

        Movie existingMovie = movieRepository.findByNameAndReleaseDate(createMovieRequest.getName(), createMovieRequest.getReleaseDate());
        if(existingMovie!=null){
           throw new Exception("Movie with following details already exists:"+existingMovie.getName()+" "+existingMovie.getReleaseDate());
        }
        Movie movie = Movie.builder()
                .desc(createMovieRequest.getDesc())
                .cast(createMovieRequest.getCast())
                .genres(createMovieRequest.getGenres())
                .name(createMovieRequest.getName())
                .languages(createMovieRequest.getLanguages())
                .posterUrl(createMovieRequest.getPosterUrl())
                .durationMinutes(createMovieRequest.getDurationMinutes())
                .releaseDate(createMovieRequest.getReleaseDate())
                .rating(5)
                .build();

        return movieRepository.save(movie);
    }

    public Movie getMovieById(String movieId) {

        return movieRepository.findById(movieId).orElseThrow();
    }

    public String deleteMovieById(String movieId) {
        movieRepository.deleteById(movieId);

            return "Movie deleted successfully with ID :"+ movieId;

    }
}
