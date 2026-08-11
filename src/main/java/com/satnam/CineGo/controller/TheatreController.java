package com.satnam.CineGo.controller;


import com.satnam.CineGo.dtos.requestDTOs.CreateTheatreRequest;
import com.satnam.CineGo.model.Theatre;
import com.satnam.CineGo.services.TheatreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/theatre")
@RequiredArgsConstructor
public class TheatreController {

    private final TheatreService theatreService;

    @PostMapping("/addTheatre")
    public ResponseEntity<Theatre> addNewTheatre(@RequestBody CreateTheatreRequest createTheatreRequest){
        Theatre createdTheatre = theatreService.addNewTheatre(createTheatreRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTheatre);
    }

    @DeleteMapping("/removeTheatre")
    public ResponseEntity<Void> deleteTheatre(@RequestHeader(name = "X-THEATRE_ID")String theatreId){
        theatreService.deleteTheatre(theatreId);

        return ResponseEntity.noContent().build();
    }
}
