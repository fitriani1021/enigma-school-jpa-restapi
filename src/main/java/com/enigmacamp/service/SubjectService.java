package com.enigmacamp.service;

import com.enigmacamp.model.Subject;
import org.springframework.data.domain.Page;

public interface SubjectService {
    Page<Subject> listSubject(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception;
    Subject getById(Long id) throws Exception;
    Subject createSubject(Subject subject) throws Exception;
    void updateSubject(Subject subject, Long id) throws Exception;
    void deleteSubject(Long id) throws Exception;
}
