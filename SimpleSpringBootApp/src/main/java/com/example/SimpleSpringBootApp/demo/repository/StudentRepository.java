package com.example.SimpleSpringBootApp.demo.repository;

import com.example.SimpleSpringBootApp.demo.model.StudentTbl;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentTbl, Long> {

    StudentTbl findByName(String name);
}
