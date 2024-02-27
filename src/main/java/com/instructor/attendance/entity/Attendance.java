package com.instructor.attendance.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

/**
 * This entity is relative to attendance table in database.
 * <p>Has ManyToOne Relationship with {@link Instructor}</p>
 * @see Instructor
 */
@Entity
@Table(name = "attendance")
public class Attendance {
    // auto generated id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // timestamp in UTC
    @Column(name = "in_time", nullable = false)
    private Instant inTime;

    // this can be null - when instructor only checked in
    // timestamp in UTC
    @Column(name = "out_time")
    private Instant outTime;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Instructor instructor;

    public Attendance() {
    }

    public Attendance(Instant inTime, Instructor instructor) {
        this.inTime = inTime;
        this.instructor = instructor;
    }

    public Attendance(Instant inTime, Instant outTime, Instructor instructor) {
        this.inTime = inTime;
        this.outTime = outTime;
        this.instructor = instructor;
    }

    public long getId() {
        return id;
    }

    public Instant getInTime() {
        return inTime;
    }

    public void setInTime(Instant inTime) {
        this.inTime = inTime;
    }

    public Instant getOutTime() {
        return outTime;
    }

    public void setOutTime(Instant outTime) {
        this.outTime = outTime;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", inTime=" + inTime +
                ", outTime=" + outTime +
                ", instructor=" + instructor +
                '}';
    }
}
