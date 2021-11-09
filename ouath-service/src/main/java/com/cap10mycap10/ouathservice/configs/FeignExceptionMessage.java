package com.cap10mycap10.ouathservice.configs;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class FeignExceptionMessage {
    private int statusCode;
    private String message;
    private String timestamp;
    private HttpStatus status;
    private int errorCode;
}
