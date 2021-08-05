package com.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // below are exception code

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleExeption(StudentNotFoundException exception) {
        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // body, response code
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    // handle generic exception ("students/jkjkjkj") instead of customed exception
    public ResponseEntity<StudentErrorResponse> handleExeption(Exception exception) {
        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        // use BAD_REQUEST instead of
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // body, response code
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
