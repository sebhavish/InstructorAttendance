package com.instructor.attendance.service;

import com.instructor.attendance.dto.CheckInResponse;
import com.instructor.attendance.dto.CheckOutResponse;
import com.instructor.attendance.entity.Attendance;
import com.instructor.attendance.entity.Instructor;
import com.instructor.attendance.exception.InvalidCheckInAttempt;
import com.instructor.attendance.exception.InvalidCheckOutAttempt;
import com.instructor.attendance.exception.WorkingTooLongException;
import com.instructor.attendance.repo.IAttendanceRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;

import static java.time.temporal.ChronoUnit.HOURS;

/**
 * Provides methods to handle check in and out for the given instructor.
 * <p> Implements {@link IAttendanceService} interface.
 * It is advised to use this class for all the things related to check in and out,
 * instead of directly populating db, as it ensures the sanity of data.
 * <p>All the exception raised are handled by {@link com.instructor.attendance.exception.ExceptionHandlerController}
 * @implNote  All the time information is in UTC
 *
 * @see IAttendanceService
 * @see IAttendanceRepo
 * @see IInstructorService
 */
@Service
public class AttendanceService implements IAttendanceService{
    private static final Logger LOG = LoggerFactory.getLogger(AttendanceService.class);

    @Autowired
    private IAttendanceRepo attendanceRepo;
    @Autowired
    private IInstructorService instructorService;

    /**
     * Checks in the instructor with provided instructor id. Creates a new attendance record in the db.
     * <p> Does not allow instructor to check in, if he previously didn't check out.
     * @param instructorId id of the instructor
     * @return details of the newly created attendance object, such as id and check in time
     * @throws InvalidCheckInAttempt when the instructor didn't check out previously
     */
    @Override
    @Transactional
    public CheckInResponse checkIn(long instructorId) {
        LOG.info("Check in Attempt by instructor - " + instructorId);
        Instructor instructor = instructorService.getInstructorById(instructorId);

        if(attendanceRepo.findByInstructorIdAndOutTimeNull(instructorId).isPresent()){
            throw new InvalidCheckInAttempt("The instructor with id: " + instructorId
                    + " has not checked out previously");
        }

        Attendance checkIn = new Attendance(Instant.now(), instructor);
        Attendance savedAttendance = attendanceRepo.save(checkIn);
        LOG.info("Check in success - " + savedAttendance);
        return new CheckInResponse(savedAttendance.getId(), savedAttendance.getInTime(),
                savedAttendance.getInstructor().getId());
    }

    /**
     * Checks out the instructor with provided instructor id.
     * <p> Does not allow instructor to check out, if he previously didn't check in.
     * <p> In some cases the instructor may work overnight, to handle this, two attendance records are created
     * in db when the instructor checks out in a different day than the check in time. This will be less error-prone while
     * doing calculations comparatively.
     * <p> If the instructor checks out after 24 hours, he may be overworking and needs to contact admin for checking out.
     *
     * @param instructorId id of the instructor
     * @return details of the newly created attendance object, such as id and check in time
     * @throws InvalidCheckOutAttempt when the instructor didn't check in previously
     * @throws WorkingTooLongException when trying to check out after more than 24 hours
     */
    @Override
    @Transactional
    public CheckOutResponse checkOut(long instructorId) {
        LOG.info("Check out Attempt by instructor - " + instructorId);

        Instructor instructor = instructorService.getInstructorById(instructorId);
        Attendance attendance = attendanceRepo.findByInstructorIdAndOutTimeNull(instructorId)
                .orElseThrow(() -> new InvalidCheckOutAttempt("The instructor with id: " + instructorId
                        + " has not checked in previously"));

        LocalDate checkInDate = attendance.getInTime().atZone(ZoneId.of("UTC")).toLocalDate();
        Instant checkOutTime = Instant.now();
        LocalDate checkOutDate = checkOutTime.atZone(ZoneId.of("UTC")).toLocalDate();

        // when check out is after more than 24 hours, does not allow checking out
        // ideally need to contact admin
        if (HOURS.between(attendance.getInTime(), checkOutTime) >= 24)
            throw new WorkingTooLongException("The instructor with id: "
                    + instructorId + " has been checked in for more than 24 hours");

        // create a two attendance records one checks out at 23:59:59 and next record checks in
        // at next day 00:00
        if(checkInDate.isBefore(checkOutDate)){
            attendance.setOutTime(checkInDate.atTime(LocalTime.MAX).atZone(ZoneId.of("UTC")).toInstant());
            attendanceRepo.save(attendance);
            LOG.info("Created two attendance instances - " + instructorId);
            attendance = new Attendance(checkOutDate.atTime(LocalTime.MIN)
                    .atZone(ZoneId.of("UTC")).toInstant(), instructor);
        }

        attendance.setOutTime(checkOutTime);
        Attendance savedAttendance = attendanceRepo.save(attendance);
        LOG.info("Check out success - " + savedAttendance);
        return new CheckOutResponse(savedAttendance.getId(), savedAttendance.getOutTime(),
                savedAttendance.getInstructor().getId());
    }
}
