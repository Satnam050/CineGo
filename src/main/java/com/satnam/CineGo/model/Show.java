package com.satnam.CineGo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.satnam.CineGo.enums.ShowStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "shows")
public class Show {

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
    @JsonBackReference
    private Movie movie;

    @ManyToOne
    @JoinColumn(
            name = "screen_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "fk_show_screen")
    )
    @JsonBackReference
    private Screen screen;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
