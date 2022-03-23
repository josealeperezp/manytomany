package com.example.demomanytomany.controller;

import com.example.demomanytomany.entity.Course;
import com.example.demomanytomany.entity.Student;
import com.example.demomanytomany.repository.CourseRepository;
import com.example.demomanytomany.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class TestController {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping(value = "/student")
    public ResponseEntity<String> createStudent(@RequestBody Map<String, String> info) {
        Student student = new Student();
        student.setName(info.get("name"));
        studentRepository.saveAndFlush(student);
        return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/course")
    public ResponseEntity<String> createCourse(@RequestBody Map<String, String> info) {
        Course course = new Course();
        course.setName(info.get("name"));
        courseRepository.saveAndFlush(course);
        return ResponseEntity.ok("ok");
    }

    @PostMapping(value = "/inscribe")
    public ResponseEntity<String> inscribeStudent(@RequestBody Map<String, String> info) {
        String studentName = info.get("student");
        String courseName = info.get("course");
        Student student = studentRepository.findByName(studentName);
        Course course = courseRepository.findByName(courseName);
        if(course.getStudents() != null && !course.getStudents().isEmpty()) {
            course.getStudents().add(student);
        } else {
            Set<Student> students = new HashSet<>();
            students.add(student);
            course.setStudents(students);
        }
        courseRepository.saveAndFlush(course);
        return ResponseEntity.ok("ok");
    }

    @DeleteMapping(value = "/student/{name}")
    public ResponseEntity<String> deleteStudent(@PathVariable String name) {
        Student student = studentRepository.findByName(name);
        studentRepository.delete(student);
        return ResponseEntity.ok("ok");
    }
}
