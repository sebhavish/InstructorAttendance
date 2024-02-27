package com.instructor.attendance.dto;

import java.time.Instant;

/**
 * Response object showing attendance id and timestamp of check out for instructor
 */
public class CheckOutResponse {
    private long attendanceId;
    private Instant checkOutTime;
    private long instructorId;

    public CheckOutResponse() {
    }

    public CheckOutResponse(long attendanceId, Instant checkOutTime, long instructorId) {
        this.attendanceId = attendanceId;
        this.checkOutTime = checkOutTime;
        this.instructorId = instructorId;
    }

    public long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Instant getCheckOutTime() {
        return checkOutTime;
    }

    public void setCheckOutTime(Instant checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }
}
