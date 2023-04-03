package com.example.ariclemanagement002.exception;


import com.example.ariclemanagement002.service.response.ArticleResponse;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.util.ObjectUtils;
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

    @ExceptionHandler({ApiResponseException.class})
    public ResponseEntity<ArticleResponse> handleApiException(ApiResponseException ex) {
        if (ObjectUtils.isEmpty(ex.getData())) {
            return new ResponseEntity<>(new ArticleResponse(HttpStatus.NOT_FOUND.value(), "Not found", null), HttpStatus.NOT_FOUND);
        } else if (ex.getData().getStatus()==0) {
            return new ResponseEntity<>(new ArticleResponse(HttpStatus.NOT_FOUND.value(), "The record Deleted", null), HttpStatus.NOT_FOUND);
        } else if (ex.getData().getStatus()==1) {
            return new ResponseEntity<>(new ArticleResponse(HttpStatus.NOT_FOUND.value(), "The record Dis Activated", null),HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArticleResponse(200, "found",  ex.getData()), HttpStatus.OK);
    }
}
