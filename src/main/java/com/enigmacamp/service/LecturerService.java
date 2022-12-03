package com.enigmacamp.service;

import com.enigmacamp.model.Lecturer;
import org.springframework.data.domain.Page;

public interface LecturerService {
    Page<Lecturer> listLecturer(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;
    Lecturer getById(Long id) throws Exception;
    Lecturer createLecturer(Lecturer lecturer) throws Exception;
    void updateLecturer(Lecturer lecturer, Long id) throws Exception;
    void deleteLecturer(Long id) throws Exception;
}
