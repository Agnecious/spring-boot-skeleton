package com.example.SimpleSpringBootApp.demo.service;

import com.example.SimpleSpringBootApp.demo.dto.Student;
import com.example.SimpleSpringBootApp.demo.model.StudentTbl;
import com.example.SimpleSpringBootApp.demo.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    private Logger log = LoggerFactory.getLogger(StudentService.class);

    public void registerStudent(Student newStudent) {
        StudentTbl student  = new StudentTbl();
        student.setName(newStudent.getName());
        student.setAge(newStudent.getAge());
        student.setCourse(newStudent.getCourse());
        studentRepository.save(student);
    }

    public Iterable<StudentTbl> retrieveAllStudents() {
        return studentRepository.findAll();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void updateStudent(Student student) {
            StudentTbl studentTbl = studentRepository.findByName(student.getName());
            if (studentTbl != null) {
                student.setCourse(student.getCourse());
                student.setName(student.getName());
                student.setAge(student.getAge());
                studentRepository.save(studentTbl);
            } else {
                log.info("No student found in the database..");
            }
    }

}