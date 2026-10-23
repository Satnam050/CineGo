package com.satnam.CineGo.dtos.responseDTOs;


import com.satnam.CineGo.enums.Genre;
import com.satnam.CineGo.enums.Language;

import lombok.Builder;
import lombok.Data;

import java.util.*;

@Data
@Builder
public class MovieSummaryResponse {




        private String id;



        private String name;


        private String desc;



        private List<String> cast;

        private List<Genre> genres;

        private String posterUrl;

        private int durationMinutes;


        private List<Language> languages;

        private Date releaseDate;


        private double rating;





}
