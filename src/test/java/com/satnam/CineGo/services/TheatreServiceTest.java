package com.satnam.CineGo.services;

import java.util.ArrayList;
import java.util.List;

import com.satnam.CineGo.dtos.requestDTOs.CreateTheatreRequest;
import com.satnam.CineGo.model.Screen;
import com.satnam.CineGo.model.Theatre;
import com.satnam.CineGo.repository.TheatreRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class TheatreServiceTest {

    @Mock
    TheatreRepository theatreRepository;

    @InjectMocks
    TheatreService theatreService;

    @Test
    public void theatreShouTldAddSuccessfully(){


        //1. create data sources needed
        CreateTheatreRequest theatreRequest = new CreateTheatreRequest();
        theatreRequest.setName("abc");
        theatreRequest.setCity("delhi");
        theatreRequest.setScreens(new ArrayList<>());
        Theatre theatre = new Theatre();

        theatre.setName("abc");
        theatre.setCity("delhi");
        theatre.setScreens(new ArrayList<>());


        //2. mock the other depended ops.
        Mockito.when(theatreRepository.save(theatre)).thenReturn(theatre);

        //3/ test call the actual function with test data
       Theatre resTheatre = theatreService.addNewTheatre(theatreRequest);


        //resTheatre == theatre

//        check that returned theatre == request theatre(test case)

        //4. check the assertions based on the response.
        Assertions.assertNotNull(resTheatre);
        Assertions.assertEquals(theatre.getName(),resTheatre.getName());
        Assertions.assertEquals(theatre.getCity(), resTheatre.getCity());

        System.out.println(resTheatre);

        System.out.println("UNIT TEST RUN SUCCESS");
    }
}