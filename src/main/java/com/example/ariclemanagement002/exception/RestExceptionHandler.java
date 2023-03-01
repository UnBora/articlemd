package com.example.ariclemanagement002.exception;

import com.example.ariclemanagement002.service.response.ArticleResponse;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(Exception.class) // exception handled
    public ResponseEntity<ArticleResponse> handleExceptions() {
        HttpStatus status = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(
                new ArticleResponse(
                        400,
                        "YOUR REQUEST PARAMS NOT MATCH!",
                        null), status
        );
    }
}
