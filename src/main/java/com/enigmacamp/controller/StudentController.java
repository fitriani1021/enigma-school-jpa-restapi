package com.enigmacamp.controller;

import com.enigmacamp.model.Student;
import com.enigmacamp.model.response.ErrorResponse;
import com.enigmacamp.model.response.PagingResponse;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @GetMapping
    public ResponseEntity getAllStudent(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(defaultValue = "studentId") String orderBy) {
        try {
            Page<Student> students = studentService.listStudent(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully Get All Students", students));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01",e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity createStudent(@Valid @RequestBody Student student) throws Exception {
        Student result = studentService.createStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Student", result));
    }
}
