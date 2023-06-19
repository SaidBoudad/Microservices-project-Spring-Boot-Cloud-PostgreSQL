package com.saidboudad.student.service;

import com.saidboudad.student.DTO.StudentResponse;
import com.saidboudad.student.entity.Student;
import com.saidboudad.student.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;
    @Autowired
    private ModelMapper modelMapper;

    public void saveStudent(Student student) {
        repository.save(student);
    }

    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    public List<StudentResponse> findAllStudentsBySchool(Integer schoolId) {
        List<Student> students = repository.findAllBySchoolId(schoolId);
        List<StudentResponse> studentResponses = modelMapper.map(students,List.class);
        System.out.println("finding list of student for school " + schoolId +" "+ LocalTime.now().toString());
        return studentResponses;
    }
}