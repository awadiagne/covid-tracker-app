package com.covid.tracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.persistence.NoResultException;
import java.io.IOException;
import java.text.ParseException;

@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    private ResponseEntity<ExceptionModel> handleNoResultFound(NoResultException ex){
        ExceptionModel error = new ExceptionModel(HttpStatus.NOT_FOUND, "No Result found", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IOException.class)
    private ResponseEntity<ExceptionModel> handleIOException(IOException ex){
        ExceptionModel error = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, "Error while saving records in DB", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ParseException.class)
    private ResponseEntity<ExceptionModel> handleIOException(ParseException ex){
        ExceptionModel error = new ExceptionModel(HttpStatus.INTERNAL_SERVER_ERROR, "Error while parsing CSV File. Incorrect records", ex.getMessage());

        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
