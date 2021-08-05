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

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleExeption(StudentNotFoundException exception) {
        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // body, response code
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // Add an exception handler using @ExceptionHandler
    @ExceptionHandler
    // handle generic exception ("students/jkjkjkj") instead of customed exception
    public ResponseEntity<StudentErrorResponse> handleExeption(Exception exception) {
        // create a StudentErrorResponse

        StudentErrorResponse error = new StudentErrorResponse();

        // use BAD_REQUEST instead of
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exception.getMessage());
        error.setTimeStamp(System.currentTimeMillis());
        // body, response code
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
