package com.saidboudad.school.service;

import com.saidboudad.school.DTO.FullSchoolResponse;
import com.saidboudad.school.client.StudentClient;
import com.saidboudad.school.entity.School;
import com.saidboudad.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service

public class SchoolService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WebClient webClient;
    @Autowired
    private SchoolRepository repository;

    @Autowired
    private StudentClient studentClient;

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

        //To call the student service using RESTTemplate
//        List<StudentResponse> students = restTemplate
//                .getForObject("http://localhost:8090/api/v1/students/school/{schoolId}",List.class,schoolId);

        //To call the student service using WebClient
//        List<StudentResponse> students = webClient
//                .get()
//                .uri("/school/"+schoolId)
//                .retrieve()
//                .bodyToMono(List.class)
//                .block();

        //To call the RestFull student service by feign client
        var students = studentClient.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}