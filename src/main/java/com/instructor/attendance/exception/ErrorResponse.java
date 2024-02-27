package com.instructor.attendance.exception;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.Instant;

/**
 * Response object in case of exceptions.
 * @see ExceptionHandlerController
 */
public class ErrorResponse {
    // status code of http response
    private int statusCode;
    // time in UTC, in dd-MM-yyyy hh:mm:ss format
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss", timezone = "UTC")
    private Instant timestamp;
    // error message
    private String message;
    // http path of the request that lead to error
    private String description;

    public ErrorResponse(int statusCode, Instant timestamp, String message, String description) {
        this.statusCode = statusCode;
        this.timestamp = timestamp;
        this.message = message;
        this.description = description;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}
