package com.satnam.CineGo.services;


import com.satnam.CineGo.dtos.requestDTOs.CreateBookingRequest;
import com.satnam.CineGo.dtos.responseDTOs.BookingPendingForPayment;
import com.satnam.CineGo.enums.BookingStatus;
import com.satnam.CineGo.enums.SeatStatus;
import com.satnam.CineGo.model.Booking;
import com.satnam.CineGo.model.Seat;
import com.satnam.CineGo.model.Show;
import com.satnam.CineGo.repository.BookingRepository;
import com.satnam.CineGo.repository.SeatRepository;
import com.satnam.CineGo.repository.ShowRepository;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class BookingService {

    private final SeatRepository seatRepository;
    private final ShowRepository showRepository;
    private final BookingRepository bookingRepository;

    public BookingService(SeatRepository seatRepository, ShowRepository showRepository, BookingRepository bookingRepository) {
        this.seatRepository = seatRepository;
        this.showRepository = showRepository;
        this.bookingRepository = bookingRepository;
    }



    @Transactional
    public BookingPendingForPayment bookTickets(CreateBookingRequest createBookingRequest) {

          /*
        params -- list seatIds and show

        1. check the availability of seat and valid show --- change the seat status to HELD
        2. calculate price
        3. pay now ---> redirect to payment service
        4. payment done
        5. change status to booked.
*/


        Show show = showRepository.findById(createBookingRequest.getShowId()).orElseThrow(()->new RuntimeException("No such show found"));

        List<String> seatIds = createBookingRequest.getSeatIds();

        if(seatIds==null || seatIds.isEmpty())throw new RuntimeException("Select atleast one seatId");

        if (new HashSet<>(seatIds).size() != seatIds.size()) {
            throw new RuntimeException("Duplicate seat IDs are not allowed");
        }

        List<Seat> seats = new ArrayList<>();

        for(String seatId : seatIds){
            Seat seat = seatRepository.findById(seatId)
                    .orElseThrow(()->new RuntimeException("Seat not found: "+seatId));

            if(!Objects.equals(seat.getShow().getId(), show.getId())){
                throw new RuntimeException("Seat not belong to this show with seatId: "+seatId);
            }


            if(seat.getStatus() != SeatStatus.AVAILABLE){
                throw new RuntimeException("Seat already booked with seatId: "+seatId);
            }

            seats.add(seat);

        }


        LocalDateTime expireTime = LocalDateTime.now().plusMinutes(5);
        for(Seat seat : seats){
            seat.setStatus(SeatStatus.HELD);
            seat.setHoldExpiresAt(expireTime);

        }
        seatRepository.saveAllAndFlush(seats);

        double price = show.getPrice()*createBookingRequest.getSeatIds().size();

        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.PENDING)
                .user(null)
                .price(price)
                .seatIds(seats.stream().map(Seat::getId).toList()).build();

        Booking bookingResponse = bookingRepository.save(booking);


     return BookingPendingForPayment.builder()
             .userId(bookingResponse.getUser().getId())
             .price(bookingResponse.getPrice())
             .bookingId(bookingResponse.getId())
             .seatIds(bookingResponse.getSeatIds())
             .bookingId(bookingResponse.getId()).build();

    }




}
