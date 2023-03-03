package com.example.ariclemanagement002.exception;

import com.example.ariclemanagement002.model.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseException extends RuntimeException {
    private Article data;


}
