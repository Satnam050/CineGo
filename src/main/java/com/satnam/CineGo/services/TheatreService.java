package com.satnam.CineGo.services;

import com.satnam.CineGo.dtos.requestDTOs.CreateTheatreRequest;
import com.satnam.CineGo.model.Screen;
import com.satnam.CineGo.model.Theatre;
import com.satnam.CineGo.repository.TheatreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class TheatreService {

    final TheatreRepository theatreRepository;

    public Theatre addNewTheatre(CreateTheatreRequest createTheatreRequest) {
        Theatre theatre = Theatre.builder()
                .name(createTheatreRequest.getName())
                .address(createTheatreRequest.getAddress())
                .city(createTheatreRequest.getCity())
                .screens(new ArrayList<>())
                .build();

        for(Screen screen : createTheatreRequest.getScreens()){
            theatre.addScreen(screen);
        }
        return theatreRepository.save(theatre);
    }


    public void deleteTheatre(String theatreId) {
        Theatre theatre = theatreRepository.findById(theatreId).orElseThrow(()->new RuntimeException("Theatre not found with given id: "+theatreId));

        theatreRepository.delete(theatre);

    }
}
