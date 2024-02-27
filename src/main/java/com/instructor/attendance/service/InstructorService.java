package com.instructor.attendance.service;

import com.instructor.attendance.entity.Instructor;
import com.instructor.attendance.exception.InstructorNotFound;
import com.instructor.attendance.repo.IInstructorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service class provides create, retrieve update operations for an instructor.
 * @see IInstructorRepo
 * @see IInstructorService
 * @see Instructor
 */
@Service
public class InstructorService implements IInstructorService{

    private static final Logger LOG = LoggerFactory.getLogger(InstructorService.class);

    @Autowired
    private IInstructorRepo instructorRepo;

    /**
     * returns the instructor object with given id
     * @param instructorId instructor id
     * @return the instructor object
     */
    @Override
    public Instructor getInstructorById(long instructorId) {
        return instructorRepo.findById(instructorId).orElseThrow(() -> new InstructorNotFound("The instructor with id - " + instructorId + " is not found"));
    }

    /**
     * Creates a new instructor with given name. id field is ignored.
     * @param instructor instructor object with name
     * @return the newly created instructor object
     */
    @Override
    public Instructor createInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    /**
     * updates the name of the instructor
     * @param instructorId id of the instructor who needs to be updated
     * @param instructor name as a json object
     * @return updated instructor object
     */
    @Override
    public Instructor updateInstructor(long instructorId, Instructor instructor) {
        Instructor oldInstructorInfo = getInstructorById(instructorId);
        oldInstructorInfo.setName(instructor.getName());

        LOG.info("Instructor updated" + oldInstructorInfo);
        return instructorRepo.save(oldInstructorInfo);
    }
}
