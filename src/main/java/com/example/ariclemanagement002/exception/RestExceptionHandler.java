package com.example.ariclemanagement002.exception;

import com.example.ariclemanagement002.model.Article;
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
            return new ResponseEntity<>(new ArticleResponse(404, "Not found", null), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(new ArticleResponse(200, "found", (Article) ex.getData()), HttpStatus.OK);
    }
}
