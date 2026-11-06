package com.satnam.CineGo.exception;

import com.satnam.CineGo.dtos.responseDTOs.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMovieNotFoundException(MovieNotFoundException movieNotFoundException){
        ErrorResponse errorResponse = new ErrorResponse(movieNotFoundException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicateMovieException(DuplicateMovieException duplicateMovieException){
        ErrorResponse errorResponse = new ErrorResponse(duplicateMovieException.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }
}

