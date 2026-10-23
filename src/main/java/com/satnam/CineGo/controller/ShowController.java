package com.satnam.CineGo.controller;

import java.util.List;

import com.satnam.CineGo.dtos.requestDTOs.CreateShowRequest;
import com.satnam.CineGo.dtos.responseDTOs.SeatResponse;
import com.satnam.CineGo.dtos.responseDTOs.ShowResponse;
import com.satnam.CineGo.model.Seat;
import com.satnam.CineGo.model.Show;
import com.satnam.CineGo.repository.SeatRepository;
import com.satnam.CineGo.services.ShowService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor

public class ShowController {


    private final ShowService showService;



    //TODO : ADD A SHOW FOR GIVEN MOVIE AND SCREEN - ADMIN ONLY

    @PostMapping("/addShow")
    public ResponseEntity<?> addNewShow(@RequestBody CreateShowRequest createShowRequest){

        try{
            ShowResponse createdShow = showService.addNewShow(createShowRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdShow);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(e.getMessage());
        }

    }

    @DeleteMapping("/deleteShow")
    public void deleteShowById(@PathVariable String showId){
        showService.deleteShowById(showId);
    }

    //TODO : GET ALL SHOWS OF GIVEN MOVIE BY id/name

    @GetMapping("/{movieId}")

    public List<ShowResponse> getAllShowsByMovieId(@PathVariable String movieId){

        return showService.getAllShowsByMovieId(movieId);

    }
    //return the seating plan of each show...
    @PreAuthorize("hasAuthority('READ')")
    @GetMapping("/seating/{showId}")
    public List<SeatResponse> getSeatsByShowId(@PathVariable String showId){
          return showService.getSeatsByShowId(showId);
    }

}
