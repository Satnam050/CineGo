package com.satnam.CineGo.dtos.responseDTOs;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class TheatreResponse {
    private String id;

    private String name;

    private String address;

    private String city;

}
