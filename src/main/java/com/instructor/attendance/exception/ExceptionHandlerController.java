package com.instructor.attendance.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

/**
 * This class handles all the thrown exceptions globally, processes the exceptions and send the response as a json.
 * @see ErrorResponse
 */
@RestControllerAdvice
public class ExceptionHandlerController {
    private static final Logger LOG = LoggerFactory.getLogger(ExceptionHandlerController.class);

    // handles if the instructor is not found
    @ExceptionHandler({InstructorNotFound.class})
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorResponse instructorNotFoundExceptionHandler(InstructorNotFound ex, WebRequest request) {
        LOG.error(ex.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                Instant.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    // handles all check in and check out related exceptions
    @ExceptionHandler({InvalidCheckInAttempt.class, InvalidCheckOutAttempt.class, WorkingTooLongException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse attendanceExceptionsHandler(RuntimeException ex, WebRequest request) {
        LOG.warn(ex.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                Instant.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }

    // handles all validation related exceptions
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
        LOG.error("Invalid request body");
        ErrorResponse message = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                Instant.now(),
                "Invalid request body",
                request.getDescription(false)
        );
        return message;
    }

    // this method is to handle all remaining kinds of exceptions, apart from those above.
    // by default results in http 500 response code
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse globalExceptionHandler(Exception ex, WebRequest request) {
        LOG.error(ex.getMessage());
        ErrorResponse message = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                Instant.now(),
                ex.getMessage(),
                request.getDescription(false)
        );
        return message;
    }
}
