package com.instructor.attendance.exception;

/**
 * This exception is thrown when the instructor tries to check in, but it is invalid.
 * @see com.instructor.attendance.service.AttendanceService
 */
public class InvalidCheckInAttempt extends RuntimeException{
    public InvalidCheckInAttempt(String message) {
        super(message);
    }
}
