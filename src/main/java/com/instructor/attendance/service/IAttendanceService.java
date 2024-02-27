package com.instructor.attendance.service;

import com.instructor.attendance.dto.CheckInResponse;
import com.instructor.attendance.dto.CheckOutResponse;

/**
 * Attendance service interface that should be implemented by the classes providing
 * check in and check out operations.
 * <p> Implemented by {@link AttendanceService}
 * @see CheckInResponse
 * @see CheckOutResponse
 * @see AttendanceService
 */
public interface IAttendanceService {
    public CheckInResponse checkIn(long instructorId);
    public CheckOutResponse checkOut(long instructorId);
}
