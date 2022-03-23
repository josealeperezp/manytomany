package com.example.demomanytomany.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Student {
    @GeneratedValue
    @Id
    Long id;

    String name;

    //@Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    //@ManyToMany(mappedBy = "students", cascade = CascadeType.ALL)
    //@ManyToMany(mappedBy = "students", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    //@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "course_student",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    Set<Course> courses;

    /*@PreRemove
    private void removeCoursesFromStudent() {
        for(Course c : courses) {
            if(c!= null && c.getStudents() != null) {
                c.getStudents().remove(this);
            }
        }
    }*/

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
