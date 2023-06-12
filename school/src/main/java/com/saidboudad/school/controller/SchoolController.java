package com.saidboudad.school.controller;

import com.saidboudad.school.DTO.FullSchoolResponse;
import com.saidboudad.school.entity.School;
import com.saidboudad.school.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/api/v1/schools")
public class SchoolController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SchoolService service;


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
        return ResponseEntity.ok(service.findSchoolsWithStudents(schoolId));
    }
}

