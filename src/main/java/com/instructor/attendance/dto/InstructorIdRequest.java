package com.instructor.attendance.dto;

import jakarta.validation.constraints.NotNull;

/**
 * request object for instructor id
 * @see com.instructor.attendance.controller.AttendanceController
 */
public class InstructorIdRequest {
    @NotNull
    private long id;

    public InstructorIdRequest() {
    }

    public InstructorIdRequest(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
