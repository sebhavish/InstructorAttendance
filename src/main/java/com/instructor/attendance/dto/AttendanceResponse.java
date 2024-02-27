package com.instructor.attendance.dto;

/**
 * Response object showing attendance data for an instructor
 */
public class AttendanceResponse {
    long instructorId;
    long timeInMinutes;

    public AttendanceResponse() {
    }

    public AttendanceResponse(long instructorId, long timeInMinutes) {
        this.instructorId = instructorId;
        this.timeInMinutes = timeInMinutes;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }

    public long getTimeInMinutes() {
        return timeInMinutes;
    }

    public void setTimeInMinutes(long timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }
}
