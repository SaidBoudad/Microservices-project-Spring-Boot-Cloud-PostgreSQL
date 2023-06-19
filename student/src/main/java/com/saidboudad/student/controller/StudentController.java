package com.saidboudad.student.controller;

import com.saidboudad.student.DTO.StudentResponse;
import com.saidboudad.student.entity.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
    private StudentService studentService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody Student student) {
        studentService.saveStudent(student);
    }

    @GetMapping
    public ResponseEntity<List<Student>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents());
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentResponse> updateStudent(@PathVariable Integer id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        if (updatedStudent != null) {
            StudentResponse response = modelMapper.map(updatedStudent, StudentResponse.class);
            String message = "Student updated successfully";
            response.setMessage(message);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/school/{school-id}")
    public ResponseEntity<List<StudentResponse>> findAllStudents(
            @PathVariable("school-id") Integer schoolId
    ) {
        return ResponseEntity.ok(studentService.findAllStudentsBySchool(schoolId));
    }
}
