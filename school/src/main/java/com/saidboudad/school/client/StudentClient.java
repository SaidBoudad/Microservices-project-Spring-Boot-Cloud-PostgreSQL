package com.saidboudad.school.client;

import com.saidboudad.school.DTO.StudentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@FeignClient(name = "student-service", url = "${application.config.students-url}")
@FeignClient(name="student-service",path = "/api/v1/students")
public interface StudentClient {

    @GetMapping("/school/{school-id}")
    List<StudentResponse> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);

}
