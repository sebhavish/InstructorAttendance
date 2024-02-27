package com.instructor.attendance.dto;

import java.time.Instant;

/**
 * Response object showing attendance id and timestamp of check in for instructor
 */
public class CheckInResponse {
    private long attendanceId;
    private Instant checkInTime;
    private long instructorId;

    public CheckInResponse() {
    }

    public CheckInResponse(long attendanceId, Instant checkInTime, long instructorId) {
        this.attendanceId = attendanceId;
        this.checkInTime = checkInTime;
        this.instructorId = instructorId;
    }

    public long getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(long attendanceId) {
        this.attendanceId = attendanceId;
    }

    public Instant getCheckInTime() {
        return checkInTime;
    }

    public void setCheckInTime(Instant checkInTime) {
        this.checkInTime = checkInTime;
    }

    public long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(long instructorId) {
        this.instructorId = instructorId;
    }
}
