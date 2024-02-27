package com.instructor.attendance.exception;

/**
 * This exception is thrown when the instructor tries to check out, but it is invalid.
 * @see com.instructor.attendance.service.AttendanceService
 */
public class InvalidCheckOutAttempt extends RuntimeException {
    public InvalidCheckOutAttempt(String message) {
        super(message);
    }
}
