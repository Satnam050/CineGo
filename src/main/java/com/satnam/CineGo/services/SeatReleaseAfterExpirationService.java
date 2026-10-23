package com.satnam.CineGo.services;

import java.time.LocalDateTime;
import java.util.List;

import com.satnam.CineGo.enums.SeatStatus;
import com.satnam.CineGo.model.Seat;
import com.satnam.CineGo.repository.SeatRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SeatReleaseAfterExpirationService {

    SeatRepository seatRepository;

    public SeatReleaseAfterExpirationService(SeatRepository seatRepository){
        this.seatRepository=seatRepository;
    }

//    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void releaseExpiredSeats(){
        List<Seat> expiredSeats = seatRepository.findByStatusAndHoldExpiresAtBefore(SeatStatus.HELD, LocalDateTime.now());

        for(Seat expiredSeat : expiredSeats){
            expiredSeat.setStatus(SeatStatus.AVAILABLE);
            expiredSeat.setHoldExpiresAt(null);
        }
        if(!expiredSeats.isEmpty())seatRepository.saveAll(expiredSeats);
    }

}
