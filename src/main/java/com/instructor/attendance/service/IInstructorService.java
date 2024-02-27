package com.instructor.attendance.service;

import com.instructor.attendance.entity.Instructor;

/**
 * Instructor service interface that should be implemented by the classes providing CRU operations
 * on Instructor table.
 * <p> Implemented by {@link InstructorService}
 * @see Instructor
 * @see InstructorService
 */
public interface IInstructorService {
    public Instructor getInstructorById(long instructorId);
    public Instructor createInstructor(Instructor instructor);
    public Instructor updateInstructor(long instructorId, Instructor instructor);
}
