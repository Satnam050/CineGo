package com.satnam.CineGo.services;

import com.satnam.CineGo.dtos.requestDTOs.CreateMovieRequest;
import com.satnam.CineGo.dtos.responseDTOs.MovieSummaryResponse;
import com.satnam.CineGo.dtos.responseDTOs.ShowResponse;
import com.satnam.CineGo.exception.DuplicateMovieException;
import com.satnam.CineGo.exception.MovieNotFoundException;
import com.satnam.CineGo.model.Movie;
import com.satnam.CineGo.repository.MovieRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }



    public static MovieSummaryResponse mapToMovieSummaryResponse(Movie movie){
        return MovieSummaryResponse.builder()
                .id(movie.getId())
                .desc(movie.getDesc())
                .durationMinutes(movie.getDurationMinutes())
                .cast(movie.getCast())
                .genres(movie.getGenres())
                .rating(movie.getRating())
                .posterUrl(movie.getPosterUrl())
                .releaseDate(movie.getReleaseDate())
                .name(movie.getName())
                .languages(movie.getLanguages())
                .build();

    }




    public List<MovieSummaryResponse> getAllMovies() {
       List<Movie> movies = movieRepository.findAll();

       return movies.stream().map(MovieService::mapToMovieSummaryResponse).toList();

    }

    public MovieSummaryResponse addNewMovie(CreateMovieRequest createMovieRequest) {

        Movie existingMovie = movieRepository.findByNameAndReleaseDate(createMovieRequest.getName(), createMovieRequest.getReleaseDate());
        if(existingMovie!=null){
           throw new DuplicateMovieException("Movie with following details already exists:"+existingMovie.getName()+" "+existingMovie.getReleaseDate());
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


       Movie savedMovie = movieRepository.save(movie);

        return mapToMovieSummaryResponse(savedMovie);

    }

    public MovieSummaryResponse getMovieById(String movieId) {
        Movie movie = movieRepository
                .findById(movieId)
                .orElseThrow(()->new MovieNotFoundException("Movie not found with given movieId"));

        return mapToMovieSummaryResponse(movie);


    }

    public String deleteMovieById(String movieId) {
        movieRepository.deleteById(movieId);

            return "Movie deleted successfully with ID :"+ movieId;

    }

    public MovieSummaryResponse getMovieByName(String movieName) {
        Movie movie = movieRepository.findByName(movieName);

        if(movie==null)throw new MovieNotFoundException("No movie exists with name :"+movieName);

        return MovieSummaryResponse.builder()
                .name(movie.getName())
                .id(movie.getId())
                .desc(movie.getDesc())
                .languages(movie.getLanguages())
                .cast(movie.getCast())
        .build();
    }
}
