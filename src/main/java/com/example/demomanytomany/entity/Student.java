package com.example.demomanytomany.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @GeneratedValue
    @Id
    Long id;

    String name;

    @ManyToMany(mappedBy = "students", cascade = CascadeType.REMOVE)
    Set<Course> courses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
