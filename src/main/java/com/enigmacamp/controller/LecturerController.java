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
        }catch(Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("X01",e.getMessage()));
        }
    }
    
    @PostMapping
    public ResponseEntity createLecturer(@Valid @RequestBody Lecturer lecturer) throws Exception {
        Lecturer result = lecturerService.createLecturer(lecturer);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>("Successfully Create Lecturer", result));
    }
}
