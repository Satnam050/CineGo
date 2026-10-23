package com.satnam.CineGo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.satnam.CineGo.enums.SeatStatus;
import com.satnam.CineGo.enums.ShowStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.List;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shows")
public class Show {

    public void generateSeats(int rows, int seatsPerRow){
        for(int i=1; i<=rows; i++){
            for(int j =1; j<=seatsPerRow; j++){
                Seat seat = new Seat();
                seat.setShow(this);
                seat.setSeatNumber(j);
                seat.setRowNumber(i);
                seat.setStatus(SeatStatus.AVAILABLE);

                seatList.add(seat);

            }

        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "show_id")
    private String id;

    private double price;

    @Enumerated(EnumType.STRING)
    private ShowStatus status;

    @Column(name = "show_start_time")
    private LocalDateTime startTime;

    @Column(name = "show_end_time")
    private LocalDateTime endTime;


    @ManyToOne
    @JoinColumn(name = "movie_id",
    nullable = false,
            foreignKey = @ForeignKey(name = "fk_show_movie")
    )

    private Movie movie;

    @ManyToOne
    @JoinColumn(
            name = "screen_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_show_screen")
    )
    @JsonBackReference
    private Screen screen;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL)
    @Builder.Default
    private List<Seat>seatList= new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
