package com.enigmacamp.controller;

import com.enigmacamp.model.Lecturer;
import com.enigmacamp.model.response.ErrorResponse;
import com.enigmacamp.model.response.PagingResponse;
import com.enigmacamp.model.response.SuccessResponse;
import com.enigmacamp.service.LecturerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/lecturers")
@Validated
public class LecturerController {
    @Autowired
    private LecturerService lecturerService;
    
    @GetMapping
    public ResponseEntity getAllLecturer(@RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "5") Integer pageSize, @RequestParam(defaultValue = "ASC") String sortDirection, @RequestParam(defaultValue = "lecturerId") String orderBy) {
        try {
            Page<Lecturer> lecturers = lecturerService.listLecturer(page, pageSize, sortDirection, orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Successfully Get All Lecturers", lecturers));
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01", e.getMessage()));
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getLecturerById(@PathVariable("id") Long id) throws Exception {
        Lecturer lecturer = lecturerService.getById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully find lecturer id "+id, lecturer));
    }
    
    @PostMapping
    public ResponseEntity createLecturer(@Valid @RequestBody Lecturer lecturer) throws Exception {
        Lecturer result = lecturerService.createLecturer(lecturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Lecturer", result));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity updateLecturer(@Valid @RequestBody Lecturer lecturer, @PathVariable("id") Long id) throws Exception {
        lecturerService.updateLecturer(lecturer, id);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Successfully Update Lecturer "+id, lecturer));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity deleteLecturer(@PathVariable("id") Long id) throws Exception {
        lecturerService.deleteLecturer(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(new SuccessResponse<>("", "Successfully deleted lecturer "+id));
    }
}