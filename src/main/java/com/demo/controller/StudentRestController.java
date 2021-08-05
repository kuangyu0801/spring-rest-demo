package com.demo.controller;

import com.demo.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // this method will only execute once
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Poor", "Patel"));
        students.add(new Student("Elon", "Musk"));
        students.add(new Student("Bill", "Gates"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        // spring will automatically handle data-binding
        return students;
    }

    // define endpoint for "students/{studentId}" - return student at index
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // index into list

        // check the studentId against list size
        if (studentId >= students.size() || studentId < 0) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return students.get(studentId);
    }


}
