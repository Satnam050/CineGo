package com.satnam.CineGo.dtos.requestDTOs;

import com.satnam.CineGo.model.Screen;
import lombok.Data;

import java.util.List;

@Data
public class CreateTheatreRequest {

    private String name;

    private String address;

    private String city;

    private List<Screen> screens;
}
