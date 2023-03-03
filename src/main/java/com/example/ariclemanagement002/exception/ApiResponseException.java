package com.example.ariclemanagement002.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseException extends RuntimeException {
    private Object data;


}
