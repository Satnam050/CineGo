package com.satnam.CineGo.dtos.requestDTOs;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreateBookingRequest {

    List<String>seatIds;
    String showId;
}
