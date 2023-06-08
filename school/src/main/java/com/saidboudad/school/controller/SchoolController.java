package com.saidboudad.school.controller;

import com.saidboudad.school.DTO.FullSchoolResponse;
import com.saidboudad.school.entity.School;
import com.saidboudad.school.service.SchoolService;
import com.saidboudad.student.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final RestTemplate restTemplate;

    private final String studentsServiceUrl = "http://student-service/api/v1/students";

    private final SchoolService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody School school) {
        service.saveSchool(school);
    }

    @GetMapping
    public ResponseEntity<List<School>> findAllSchools() {
        return ResponseEntity.ok(service.findAllSchools());
    }

    @GetMapping("/with-students/{school-id}")
    public ResponseEntity<FullSchoolResponse> findAllStudentsBySchoolId(
            @PathVariable("school-id") Integer schoolId
    ) {
        List<Student> students = restTemplate.getForObject(studentsServiceUrl + "/school/" + schoolId, List.class);
        // Process the students as needed
        // ...

        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }
}

