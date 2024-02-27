package com.instructor.attendance.controller;

import com.instructor.attendance.entity.Instructor;
import com.instructor.attendance.service.IInstructorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * This controller allows to create, update and retrieve instructor.
 * @see Instructor
 * @see IInstructorService
 */
@RestController
@RequestMapping("api/instructors")
public class InstructorController {
    @Autowired
    private IInstructorService instructorService;

    /**
     * @param instructorId instructor id
     * @return Instructor object if available
     */
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable("id") long instructorId) {
        Instructor instructor = instructorService.getInstructorById(instructorId);
        return new ResponseEntity<>(instructor, HttpStatus.OK);
    }

    /**
     * Creates a new instructor row in db and returns the instructor object.
     * <p> {@code name} field in the Instructor should not be blank or null.
     * @implNote {@code id} field is not need to be provided in the request body, even if it is provided, it will be ignored
     * @param instructor instructor object
     * @return the newly created instructor object along with id.
     */
    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody @Valid Instructor instructor) {
        Instructor createdInstructor = instructorService.createInstructor(instructor);
        return new ResponseEntity<>(createdInstructor, HttpStatus.OK);
    }

    /**
     * to update the name information for the instructor with given id
     * @param instructorId id of instructor to be updated
     * @param instructor instructor object with name
     * @return the updated instructor object
     */
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable("id") long instructorId, @RequestBody @Valid Instructor instructor) {
        Instructor updatedInstructor = instructorService.updateInstructor(instructorId, instructor);
        return new ResponseEntity<>(updatedInstructor, HttpStatus.OK);
    }

    // No delete for now, since we only do soft delete in general.
}
