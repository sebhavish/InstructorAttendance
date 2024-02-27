package com.instructor.attendance.repo;

import com.instructor.attendance.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

/**
 * Jpa Repository of Attendance entity that enables querying of the attendance table.
 */
@Repository
public interface IAttendanceRepo extends JpaRepository<Attendance, Long> {
    /**
     * Returns the rows, of the instructor where the in time is between provided start and end timestamps(both inclusive).
     * @param start start time
     * @param end end time
     * @param instructorId id of instructor
     * @return list of attendance objects
     */
    List<Attendance> findByInTimeBetweenAndInstructorId(Instant start, Instant end, long instructorId);

    /**
     * Returns the row from attendance table, where out time is null for the provided instructor.
     * <p>this will return the row where instructor is currently checked in and didn't check out yet.
     * @param instructorId id of the instructor
     * @return the attendance object if exists
     */
    Optional<Attendance> findByInstructorIdAndOutTimeNull(long instructorId);
}
