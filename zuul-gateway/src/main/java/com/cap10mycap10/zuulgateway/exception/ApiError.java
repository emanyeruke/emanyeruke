package com.cap10mycap10.zuulgateway.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiError {

    private String timestamp;
    private int statusCode;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError(String timestamp, int statusCode, HttpStatus status, String message, List<String> errors) {
        super();
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(String timestamp, int statusCode, HttpStatus status, String message, String error) {
        super();
        this.timestamp = timestamp;
        this.statusCode = statusCode;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}
