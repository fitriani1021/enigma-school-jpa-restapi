package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.model.Subject;
import com.enigmacamp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements SubjectService{
    @Autowired
    private SubjectRepository subjectRepository;
    
    @Override
    public Page<Subject> listSubject(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        Page<Subject> result = subjectRepository.findAll(pageable);
        return result;
    }
    
    @Override
    public Subject createSubject(Subject subject) throws Exception {
        try {
            Subject newSubject = subjectRepository.save(subject);
            return newSubject;
        }catch(DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }
}
