package com.satnam.CineGo.dtos.responseDTOs;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TicketResponse {
    private String id;

    private List<String> seatNos;

    private double amount;
}
