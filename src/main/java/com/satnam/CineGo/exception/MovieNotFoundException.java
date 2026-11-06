package com.satnam.CineGo.exception;

public class MovieNotFoundException extends RuntimeException {
    public MovieNotFoundException(String message){
            super(message);
    }
}
