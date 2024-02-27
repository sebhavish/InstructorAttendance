package com.instructor.attendance.exception;

/**
 * This exception is thrown when the instructor tries to check out, but the time elapsed is too long.
 * @see com.instructor.attendance.service.AttendanceService
 */
public class WorkingTooLongException extends RuntimeException{

    public WorkingTooLongException(String message) {
        super(message);
    }
}
