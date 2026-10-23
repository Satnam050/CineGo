package com.satnam.CineGo.repository;

import com.satnam.CineGo.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,String> {
}
