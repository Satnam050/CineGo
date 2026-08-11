package com.satnam.CineGo.dtos.requestDTOs;

import com.satnam.CineGo.enums.ShowStatus;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class CreateShowRequest {

    private String movieId;

    private String screenId;

    private double price;

    private ShowStatus status;

    private LocalDateTime startTime;

    private LocalDateTime endTime;


}
