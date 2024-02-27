package com.instructor.attendance.exception;

/**
 * This exception is thrown when the instructor is not found
 */
public class InstructorNotFound extends RuntimeException{
    public InstructorNotFound(String message) {
        super(message);
    }
}
