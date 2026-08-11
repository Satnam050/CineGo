package com.satnam.CineGo.dtos.requestDTOs;


import com.satnam.CineGo.enums.Genre;
import com.satnam.CineGo.enums.Language;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
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
