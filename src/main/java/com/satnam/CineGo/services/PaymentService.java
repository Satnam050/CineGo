package com.satnam.CineGo.services;
import java.util.ArrayList;
import java.util.List;

import com.satnam.CineGo.dtos.responseDTOs.TicketResponse;
import com.satnam.CineGo.enums.BookingStatus;
import com.satnam.CineGo.enums.SeatStatus;
import com.satnam.CineGo.model.Booking;
import com.satnam.CineGo.model.Seat;
import com.satnam.CineGo.model.Ticket;
import com.satnam.CineGo.repository.BookingRepository;
import com.satnam.CineGo.repository.SeatRepository;
import com.satnam.CineGo.repository.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService{

    private final TicketRepository ticketRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    PaymentService(TicketRepository ticketRepository, SeatRepository seatRepository, BookingRepository bookingRepository){
        this.ticketRepository=ticketRepository;
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
    }
    //TODO : ADD ENTIRE PAYMENT SYSTEM - razorpay or stripe...

    @Transactional
   public TicketResponse payment(String bookingId){

            System.out.println("payment done successfully");


            Booking bookingBeforePayment = bookingRepository.findById(bookingId)
                    .orElseThrow(()->new RuntimeException("No booking found with this booking id "+bookingId));



            List<String>seatNosList = new ArrayList<>();

            for(String seatId : bookingBeforePayment.getSeatIds()){
                Seat seat = seatRepository.findById(seatId).orElseThrow(()->new RuntimeException("No seat found"));

                if(seat.getStatus()==SeatStatus.BOOKED) throw new RuntimeException("Already booked seat");

                seat.setStatus(SeatStatus.BOOKED);
                seat.setHoldExpiresAt(null);

                seatRepository.save(seat);
                bookingBeforePayment.setBookingStatus(BookingStatus.CONFIRMED);

                seatNosList.add("R"+ seat.getRowNumber()+ " S"+seat.getSeatNumber());

            }


            Ticket savedTicket = ticketRepository.save(Ticket.builder()
                    .amount(bookingBeforePayment.getPrice())
                    .seatNos(seatNosList)
                    .build());

            return TicketResponse.builder()
                    .seatNos(savedTicket.getSeatNos())
                    .amount(savedTicket.getAmount())
                    .id(savedTicket.getId())
                    .build();


   }

}
