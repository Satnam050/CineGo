package com.satnam.CineGo.model;


import com.satnam.CineGo.enums.Genre;
import com.satnam.CineGo.enums.Language;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "movie_id")
    private String id;


    @Column(name = "movie_name", unique = true)
    private String name;

    @Column(name = "movie_desc", length = 3000)
    private String desc;


    /*TODO : it should be implemented using @ElementCollection for now it is stored as JSON
            but later if we want to search using cast then it should have a seperate table
     */

    @Column(name = "movie_cast")
    private List<String> cast;


    @Column(name = "movie_genres")
    @Enumerated(EnumType.STRING)
    private List<Genre> genres;

    private String posterUrl;

    private int durationMinutes;

    @Enumerated(EnumType.STRING)
    private List<Language> languages;

    private Date releaseDate;

    @Column(name = "movie_rating")
    private double rating;

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Show>shows;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
