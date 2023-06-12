package com.saidboudad.school.client;

//@FeignClient(name = "student-service", url = "${application.config.students-url}")
public interface StudentClient {

//    @GetMapping("/school/{school-id}")
//    List<StudentResponse> findAllStudentsBySchool(@PathVariable("school-id") Integer schoolId);
}
