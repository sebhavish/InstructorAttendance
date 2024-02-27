package com.instructor.attendance.paging;

import com.instructor.attendance.dto.AttendanceResponse;

import java.util.List;

/**
 * Wrapper object that is helpful with paginated responses.
 * <p>Mainly helpful in reports related services and controllers</p>
 * @see com.instructor.attendance.service.IReportsService
 * @see com.instructor.attendance.controller.ReportsController
 */
public class PageWrapper {
    private long totalInstructors;
    // Attendance data for each instructor in the page
    private List<AttendanceResponse> instructors;
    private int currentPage;
    private int totalPages;

    public PageWrapper(long totalInstructors, List<AttendanceResponse> instructors, int currentPage, int totalPages) {
        this.totalInstructors = totalInstructors;
        this.instructors = instructors;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
    }

    public long getTotalInstructors() {
        return totalInstructors;
    }

    public List<AttendanceResponse> getInstructors() {
        return instructors;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }
}
