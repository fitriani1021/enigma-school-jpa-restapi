package com.enigmacamp.service;

import com.enigmacamp.model.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<Student> listStudent(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;
    Student createStudent(Student student) throws Exception;
}
