package com.instructor.attendance.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * This entity is relative to instructor table in database.
 * <p>Has One to Many Relationship with {@link Attendance}</p>
 * @see Attendance
 */
@Entity
@Table(name = "instructor")
public class Instructor {
    // auto generated id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // should not be null or blank
    @Column(nullable = false)
    @NotBlank
    private String name;

    public Instructor() {
    }

    public Instructor(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
