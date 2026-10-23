package com.satnam.CineGo.model;


import com.satnam.CineGo.enums.SeatStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "seat_row")
    private int rowNumber;

    @Column(name = "seat_number")
    private int seatNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status=SeatStatus.AVAILABLE;

    @Version
    private long version;



    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    private LocalDateTime holdExpiresAt;


}
