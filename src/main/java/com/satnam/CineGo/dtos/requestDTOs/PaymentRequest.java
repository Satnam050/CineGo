package com.satnam.CineGo.dtos.requestDTOs;

import com.satnam.CineGo.dtos.responseDTOs.SeatResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {

    double price;
    List<SeatResponse> seats;
}
