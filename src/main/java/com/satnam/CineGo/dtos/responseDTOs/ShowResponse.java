package com.satnam.CineGo.dtos.responseDTOs;

import com.satnam.CineGo.enums.ShowStatus;

import com.satnam.CineGo.model.Screen;

import lombok.Builder;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Builder
public class ShowResponse {



        private String id;

        private double price;


        private ShowStatus status;


        private LocalDateTime startTime;


        private LocalDateTime endTime;


        private String movieId;


        private ScreenResponse screen;


}
