package com.saidboudad.school.service;

import com.saidboudad.school.DTO.FullSchoolResponse;
import com.saidboudad.school.DTO.StudentResponse;
import com.saidboudad.school.entity.School;
import com.saidboudad.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service

public class SchoolService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SchoolRepository repository;
//    @Autowired
//    private StudentClient client;

    public void saveSchool(School school) {
        repository.save(school);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }

    public FullSchoolResponse findSchoolsWithStudents(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        //var students = client.findAllStudentsBySchool(schoolId);
        List<StudentResponse> students = restTemplate
                .getForObject("http://localhost:8090/api/v1/students/school/{schoolId}",List.class,schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}