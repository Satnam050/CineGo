package com.satnam.CineGo.dtos.responseDTOs;
import java.util.List;


import com.satnam.CineGo.enums.BookingStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class BookingPendingForPayment {

    private String bookingId;

    private String userId;

    private double price;

    private List<String> seatIds;

    private BookingStatus bookingStatus;
}
