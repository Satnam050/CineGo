package com.satnam.CineGo.controller;

import com.satnam.CineGo.dtos.requestDTOs.CreateBookingRequest;
import com.satnam.CineGo.dtos.responseDTOs.BookingPendingForPayment;
import com.satnam.CineGo.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/booking")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<BookingPendingForPayment> bookTickets(@RequestBody CreateBookingRequest createBookingRequest){

            try {
                BookingPendingForPayment response = bookingService.bookTickets(createBookingRequest);
                return ResponseEntity.status(HttpStatus.CREATED).body(response);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

    }

}
