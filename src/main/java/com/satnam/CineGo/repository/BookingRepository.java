package com.satnam.CineGo.repository;

import com.satnam.CineGo.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, String> {
}
