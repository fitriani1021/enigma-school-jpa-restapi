package com.enigmacamp.controller;

import com.enigmacamp.model.Lecturer;
import com.enigmacamp.model.Student;
import com.enigmacamp.model.Subject;
import com.enigmacamp.model.response.ErrorResponse;
import com.enigmacamp.model.response.PagingResponse;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/subjects")
@Validated
public class SubjectController {
    @Autowired
    private SubjectService subjectService;
    
    @GetMapping
    public ResponseEntity getAllLecturer(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(defaultValue = "subjectId") String orderBy) {
        try {
            Page<Subject> subjects = subjectService.listSubject(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully Get All Subjects", subjects));
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01",e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getStudentById(@PathVariable("id") Long id) throws Exception {
        Subject subject = subjectService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully find subject id "+id, subject));
    }
    
    @PostMapping
    public ResponseEntity createSubject(@Valid @RequestBody Subject subject) throws Exception {
        Subject result = subjectService.createSubject(subject);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Subject", result));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateLecturer(@Valid @RequestBody Subject subject, @PathVariable("id") Long id) throws Exception {
        subjectService.updateSubject(subject, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully Update subject "+id, subject));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLecturer(@PathVariable("id") Long id) throws Exception {
        subjectService.deleteSubject(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse<>("", "Successfully deleted subject "+id));
    }
}
