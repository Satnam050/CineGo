package com.satnam.CineGo.dtos.responseDTOs;

import com.satnam.CineGo.enums.SeatStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeatResponse {

    private String id;

    private int rowNumber;

    private int seatNumber;

    private SeatStatus status;


}
