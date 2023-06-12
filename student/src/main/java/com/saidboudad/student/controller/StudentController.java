package com.saidboudad.student.controller;

import com.saidboudad.student.DTO.StudentResponse;
import com.saidboudad.student.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.saidboudad.student.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    @Autowired
    private StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Student student) {
        service.saveStudent(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(service.findAllStudents());
    }

    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<StudentResponse>> findAllStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(service.findAllStudentsBySchool(schoolId));
    }
}
