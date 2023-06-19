package com.saidboudad.school.service;

import com.saidboudad.school.DTO.FullSchoolResponse;
import com.saidboudad.school.DTO.StudentResponse;
import com.saidboudad.school.client.StudentClient;
import com.saidboudad.school.entity.School;
import com.saidboudad.school.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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
    private StudentClient studentClient;
    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private SchoolRepository repository;


    public void saveSchool(School school) {
        repository.save(school);
    }

    public List<School> findAllSchools() {
        return repository.findAll();
    }

    public FullSchoolResponse callingStudentServiceUsingRESTTemplate(Integer schoolId) {
        // to make the call of student service dynamic, we can use this discoveryClient bean, but not load balanced
//        List<ServiceInstance> instances = discoveryClient.getInstances("student-service");
//        ServiceInstance serviceInstance = instances.get(0);
//        String url = serviceInstance.getUri().toString() + "/api/v1/students";

        //using the LoadBalancerClient, we can load balancing the call of the service target instances
        ServiceInstance serviceInstance = loadBalancerClient.choose("student-service");
//        String uri = serviceInstance.getUri().toString();

       //fetching configPath dynamically from eureka |meta-data map
        String contextRoot = serviceInstance.getMetadata().get("configPath");
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        List<StudentResponse> students = restTemplate
//                .getForObject(uri + contextRoot + "/school/{schoolId}",List.class,schoolId);
                .getForObject("http://student-service" + contextRoot + "/school/{schoolId}",List.class,schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
    //To call the RestFull student service using WebClient (advantages of reactive programming)
    public FullSchoolResponse callingStudentServiceUsingWebClient(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        List<StudentResponse> students = webClient
                .get()
                .uri("/school/" + schoolId)
                .retrieve()
                .bodyToMono(List.class)
                .block();
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
    //To call the RestFull student service using feign client
    public FullSchoolResponse callingStudentServiceUsingOpenFeign(Integer schoolId) {
        var school = repository.findById(schoolId)
                .orElse(
                        School.builder()
                                .name("NOT_FOUND")
                                .email("NOT_FOUND")
                                .build()
                );
        var students = studentClient.findAllStudentsBySchool(schoolId);
        return FullSchoolResponse.builder()
                .name(school.getName())
                .email(school.getEmail())
                .students(students)
                .build();
    }
}