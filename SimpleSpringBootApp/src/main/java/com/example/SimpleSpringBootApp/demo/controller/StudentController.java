package com.example.SimpleSpringBootApp.demo.controller;

import com.example.SimpleSpringBootApp.demo.dto.Student;
import com.example.SimpleSpringBootApp.demo.model.StudentTbl;
import com.example.SimpleSpringBootApp.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Value("${openshift.secret}")
    private String valueFromOpenshift;

    @PostMapping("/register")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        studentService.registerStudent(student);
        return new ResponseEntity<>("Registered " + student.getName() + "as new student", HttpStatus.CREATED);

    }


    @GetMapping("/test")
    public String testApi() {
        log.info("Request received from the client..");
        log.info("Openshift secret value extracted: "+valueFromOpenshift);
        return "Success";
    }

    @GetMapping("/test2")
    public String testApi2() {
        log.info("Request received from the client..[LOCAL]");
        return "I AM TEST 2";
    }
    @GetMapping("/retrieveAllStudents")
    public Iterable<StudentTbl> retrieveAllStudents() {
        return studentService.retrieveAllStudents();
    }

    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudentById(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return new ResponseEntity<>("User with id : "+id+" has been deleted", HttpStatus.ACCEPTED);

    }

    @PostMapping("/updateStudent")
    public void updateStudentDetails(@RequestBody Student student) {
        studentService.updateStudent(student);

    }

    //retrieve list
    //search list
    //filter
    //save
    //delete
    //update
}
