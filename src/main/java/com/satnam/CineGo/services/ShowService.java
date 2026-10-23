package com.satnam.CineGo.services;

import com.satnam.CineGo.dtos.requestDTOs.CreateShowRequest;
import com.satnam.CineGo.dtos.responseDTOs.ScreenResponse;
import com.satnam.CineGo.dtos.responseDTOs.SeatResponse;
import com.satnam.CineGo.dtos.responseDTOs.ShowResponse;
import com.satnam.CineGo.dtos.responseDTOs.TheatreResponse;
import com.satnam.CineGo.model.*;
import com.satnam.CineGo.repository.MovieRepository;
import com.satnam.CineGo.repository.ScreenRepository;
import com.satnam.CineGo.repository.SeatRepository;
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
    private final SeatRepository seatRepository;


    public ShowResponse addNewShow(CreateShowRequest createShowRequest) {

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

        show.generateSeats(5, 10);


        Show savedShow = showRepository.save(show);

        return mapToShowResponse(savedShow);
    }

    public void deleteShowById(String showId) {
        showRepository.deleteById(showId);
    }


    public TheatreResponse mapToTheatreResponse(Theatre theatre){
        return TheatreResponse.builder()
                .name(theatre.getName())
                .id(theatre.getId())
                .city(theatre.getCity())
                .address(theatre.getAddress())
        .build();

    }
    public ScreenResponse mapToScreenResponse(Screen screen){
        return ScreenResponse.builder()
                .id(screen.getId())
                .screenType(screen.getScreenType())
                .theatre(mapToTheatreResponse(screen.getTheatre()))
                .name(screen.getName())
                .build();
    }

    public ShowResponse mapToShowResponse(Show show){

            return ShowResponse.builder()
                    .id(show.getId())
                    .price(show.getPrice())
                    .status(show.getStatus())
                    .startTime(show.getStartTime())
                    .endTime(show.getEndTime())
                    .movieId(show.getMovie().getId())
                    .screen(mapToScreenResponse(show.getScreen()))
                    .build();
    }

    public List<ShowResponse> getShowResponses (List<Show> shows){
        return shows.stream().map((show -> mapToShowResponse(show))).toList();
    }

    public List<ShowResponse> getAllShowsByMovieId(String movieId) {

        List<Show>showsList = showRepository.findAllByMovieId(movieId);

        return getShowResponses(showsList);


    }

    public static SeatResponse mapToSeatResponse(Seat seat){
        return SeatResponse.builder()
                .id(seat.getId())
                .seatNumber(seat.getSeatNumber())
                .rowNumber(seat.getRowNumber())
                .status(seat.getStatus())
                .build();
    }

    public List<SeatResponse> getSeatsByShowId(String showId) {
        List<Seat> seatList = seatRepository.findByShowId(showId);

        return seatList.stream().map(ShowService::mapToSeatResponse).toList();
    }
}
