package com.bankapi.bankapi.util;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiErrorResponse {

    private int status;
    private String message;
    private LocalDateTime timestamp;
    private  HttpStatus badRequest;

    public ApiErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus badRequest, String message, LocalDateTime now) {
        this.badRequest =badRequest;
        this.message = message;
        this.timestamp = now;


    }

    public HttpStatus getBadRequest() {
        return badRequest;
    }

    public void setBadRequest(HttpStatus badRequest) {
        this.badRequest = badRequest;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}