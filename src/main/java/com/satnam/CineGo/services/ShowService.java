package com.satnam.CineGo.services;

import com.satnam.CineGo.dtos.requestDTOs.CreateShowRequest;
import com.satnam.CineGo.model.Movie;
import com.satnam.CineGo.model.Screen;
import com.satnam.CineGo.model.Show;
import com.satnam.CineGo.repository.MovieRepository;
import com.satnam.CineGo.repository.ScreenRepository;
import com.satnam.CineGo.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final MovieRepository movieRepository;
    private  final ShowRepository showRepository;
    private final ScreenRepository screenRepository;


    public Show addNewShow(CreateShowRequest createShowRequest) {

        Movie movie = movieRepository.findById(createShowRequest.getMovieId()).orElseThrow(()-> new RuntimeException("Movie not found with given id"));

        Screen screen = screenRepository.findById(createShowRequest.getScreenId()).orElseThrow(()->new RuntimeException("Screen not found with given id"));


        Show show = Show.builder()
                .price(createShowRequest.getPrice())
                .screen(screen)
                .movie(movie)
                .startTime(createShowRequest.getStartTime())
                .endTime(createShowRequest.getEndTime())
                .status(createShowRequest.getStatus())
                .build();

        return showRepository.save(show);
    }

    public void deleteShowById(String showId) {
        showRepository.deleteById(showId);
    }

    public List<Show> getAllShowsByMovieId(String movieId) {

        return showRepository.findAllByMovieId(movieId);
    }
}
