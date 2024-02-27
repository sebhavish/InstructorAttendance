package com.instructor.attendance.service;

import com.instructor.attendance.dto.AttendanceResponse;
import com.instructor.attendance.entity.Attendance;
import com.instructor.attendance.entity.Instructor;
import com.instructor.attendance.paging.PageWrapper;
import com.instructor.attendance.repo.IAttendanceRepo;
import com.instructor.attendance.repo.IInstructorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.List;

/**
 * Service class helpful in generating attendance reports.
 * @see com.instructor.attendance.controller.ReportsController
 * @see IReportsService
 * @see PageWrapper
 */
@Service
public class ReportsService implements IReportsService{
    @Autowired
    private IAttendanceRepo attendanceRepo;
    @Autowired
    private IInstructorRepo instructorRepo;

    /**
     * Generate Paginated monthly attendance report for the given month and year, for all the instructors in the db.
     * @param month month
     * @param year year
     * @param pageNo zero indexed page number
     * @param pageSize number of results you want per page
     * @return Paginated response containing attendance information
     */
    @Override
    public PageWrapper getMonthlyAttendance(Month month, Year year, int pageNo, int pageSize) {
        LocalDate monthStartDate = LocalDate.of(year.getValue(), month, 1);
        Instant monthStartTime = monthStartDate.atStartOfDay().atZone(ZoneId.of("UTC")).toInstant();
        LocalDate monthEndDate = LocalDate.of(year.getValue(), month, 1)
                .plusMonths(1).minusDays(1);
        Instant monthEndTime = monthEndDate.atTime(LocalTime.MAX).atZone(ZoneId.of("UTC")).toInstant();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Instructor> instructorList = instructorRepo.findAll(pageable);

        List<AttendanceResponse> pagedAttendanceResponse =  instructorList.stream()
                .map(instructor -> calculateAttendanceInMinutes(instructor.getId(),
                        monthStartTime, monthEndTime)).toList();
        return new PageWrapper(instructorList.getTotalElements(), pagedAttendanceResponse,
                instructorList.getNumber() + 1, instructorList.getTotalPages());
    }

    /**
     * Helper method that calculates the attendance time in minutes for the given instructor id, between start time and end time (both inclusive)
     * <p> If an instructor is currently still logged in when he started his work between start time and end time, then
     * the time in minutes will be calculated until current time, if current time is before end time, otherwise time is calculated until end time.
     * @param instructorId id of the instructor
     * @param startTime start timestamp
     * @param endTime end timestamp
     * @return Response object with instructor id and attendance time in minutes
     */
    private AttendanceResponse calculateAttendanceInMinutes(long instructorId, Instant startTime, Instant endTime) {
        List<Attendance> attendanceList = attendanceRepo.findByInTimeBetweenAndInstructorId(startTime, endTime, instructorId);
        long totalTimeInMinutes = 0;
        totalTimeInMinutes += attendanceList.stream()
                .map(attendance ->{
                    Instant outTime = attendance.getOutTime();
                    Instant currentTime = Instant.now();
                    // handling the case where instructor is not yet checked out
                    if(outTime == null) {
                        outTime = currentTime.isAfter(endTime) ? endTime : currentTime;
                    } else if (outTime.isAfter(endTime)) { // when instructor checked out after end time
                        outTime = endTime;
                    }
                    return Duration.between(attendance.getInTime(), outTime).toMinutes();
                })
                .reduce(0L, Long::sum);
        return new AttendanceResponse(instructorId, totalTimeInMinutes);
    }
}
