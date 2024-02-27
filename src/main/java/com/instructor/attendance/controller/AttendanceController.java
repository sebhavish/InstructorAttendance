package com.instructor.attendance.controller;

import com.instructor.attendance.dto.CheckInResponse;
import com.instructor.attendance.dto.CheckOutResponse;
import com.instructor.attendance.dto.InstructorIdRequest;
import com.instructor.attendance.service.IAttendanceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * This controller class creates an api to handle check in and out for an instructor.
 * @see IAttendanceService
 */
@RestController
@RequestMapping("/api/attendance")
public class AttendanceController {
    @Autowired
    private IAttendanceService attendanceService;

    /**
     * Check in the instructor if the provided instructor id is valid
     * <p> Also validates if the check in is valid
     * @param instructor id of an instructor sent as a request body
     * @return json object with check in time data and newly created attendance id.
     * @see InstructorIdRequest
     * @see CheckInResponse
     */
    @PostMapping("/check-in")
    private ResponseEntity<CheckInResponse> checkIn(@Valid @RequestBody InstructorIdRequest instructor) {
        CheckInResponse checkedIn = attendanceService.checkIn(instructor.getId());
        return new ResponseEntity<>(checkedIn, HttpStatus.OK);
    }

    /**
     * Check in the instructor if the provided instructor id is valid.
     * <p> Also validates if the checkout is valid
     * @param instructor id of an instructor sent as a request body
     * @return
     */
    @PostMapping("/check-out")
    private ResponseEntity<CheckOutResponse> checkOut(@Valid @RequestBody InstructorIdRequest instructor) {
        CheckOutResponse checkedOut = attendanceService.checkOut(instructor.getId());
        return new ResponseEntity<>(checkedOut, HttpStatus.OK);
    }
}
