package com.rest.webservices.restful_web_services.exception;

import java.time.LocalDateTime;

public class ErrorDetails {
    //timestamp, message, details

    private LocalDateTime timestamp;
    private String message;
    private String details;

    @Override
    public String toString() {
        return "ErrorDetails{" +
                "timestamp=" + timestamp +
                ", message='" + message + '\'' +
                ", details='" + details + '\'' +
                '}';
    }

    public String getDetails() {
        return details;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ErrorDetails(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }
}
