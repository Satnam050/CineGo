package com.satnam.CineGo.dtos;


import com.satnam.CineGo.enums.Genre;
import com.satnam.CineGo.enums.Language;
import com.satnam.CineGo.model.Show;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
public class CreateMovieRequest {

        private String name;

        private String desc;

        private List<String> cast;

        private List<Genre> genres;

        private String posterUrl;

        private int durationMinutes;

        private List<Language> languages;

        private Date releaseDate;


}
