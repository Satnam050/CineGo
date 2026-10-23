package com.satnam.CineGo.repository;

import com.satnam.CineGo.enums.SeatStatus;
import com.satnam.CineGo.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface SeatRepository extends JpaRepository<Seat, String> {
    List<Seat> findByShowId(String showId);

    List<Seat> findByStatusAndHoldExpiresAtBefore(
            SeatStatus status,
            LocalDateTime time
    );
}
