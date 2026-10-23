package com.satnam.CineGo.dtos.responseDTOs;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.satnam.CineGo.enums.ScreenType;
import com.satnam.CineGo.model.Theatre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ScreenResponse {

    private String id;

    private String name;

    private ScreenType screenType;


    private TheatreResponse theatre;
}
