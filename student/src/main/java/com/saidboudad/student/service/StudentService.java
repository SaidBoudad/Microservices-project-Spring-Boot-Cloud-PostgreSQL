package com.saidboudad.student.service;

import com.saidboudad.student.DTO.StudentResponse;
import com.saidboudad.student.entity.Student;
import com.saidboudad.student.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Integer id, Student student) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);
        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();
            existingStudent.setFirstname(student.getFirstname());
            existingStudent.setLastname(student.getLastname());
            existingStudent.setEmail(student.getEmail());
            existingStudent.setSchoolId(student.getSchoolId());
            studentRepository.save(existingStudent);
            return existingStudent;
        } else {
            return null;
        }
    }


    public List<StudentResponse> findAllStudentsBySchool(Integer schoolId) {
        List<Student> students = studentRepository.findAllBySchoolId(schoolId);
        List<StudentResponse> studentResponses = modelMapper.map(students,List.class);
        System.out.println("finding list of student for school " + schoolId +" "+ LocalTime.now().toString());
        return studentResponses;
    }



}