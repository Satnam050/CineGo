package com.satnam.CineGo.controller;

import java.util.List;

import com.satnam.CineGo.dtos.requestDTOs.PaymentRequest;
import com.satnam.CineGo.dtos.responseDTOs.TicketResponse;
import com.satnam.CineGo.model.Seat;
import com.satnam.CineGo.model.Ticket;
import com.satnam.CineGo.repository.TicketRepository;
import com.satnam.CineGo.services.PaymentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    //this is the controller to which user gets redirected once he/she clicks pay now button

    //this should accept the details of seats, money and all and the final payment should be done here

    @PostMapping("/{bookingId}")
    public TicketResponse payment(@PathVariable String bookingId){

       TicketResponse ticketResponse = paymentService.payment(bookingId);
        return ticketResponse;
    }
}
