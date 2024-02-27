package com.instructor.attendance.repo;

import com.instructor.attendance.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Jpa Repository of Instructor entity that enables querying of the instructor table.
 */
@Repository
public interface IInstructorRepo extends JpaRepository<Instructor, Long> {

}
