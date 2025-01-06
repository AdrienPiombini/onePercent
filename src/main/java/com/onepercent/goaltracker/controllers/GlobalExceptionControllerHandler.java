package com.onepercent.goaltracker.controllers;

import com.onepercent.goaltracker.Utils.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
public class GlobalExceptionControllerHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionControllerHandler.class);

    @ExceptionHandler({RuntimeException.class, NullPointerException.class})
    public ResponseEntity<ErrorResponse> handleExceptions(
            RuntimeException exception, WebRequest request
    ){

        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage(),
                request.getDescription(false));

        log.info(exception.getMessage());
        log.info(exception.getStackTrace().toString());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }
}

