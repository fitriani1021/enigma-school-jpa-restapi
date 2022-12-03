package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Subject;
import com.enigmacamp.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Subject getById(Long id) throws Exception {
        Optional<Subject> subject =subjectRepository.findById(id);
        if(subject.isEmpty()) {
            throw new NotFoundException("Student " + id + " not found");
        }
        return subject.get();
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
    
    @Override
    public void updateSubject(Subject subject, Long id) throws Exception {
        try {
            Subject existingSubject = getById(id);
            subject.setSubjectId(existingSubject.getSubjectId());
            subjectRepository.save(subject);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }
    
    @Override
    public void deleteSubject(Long id) throws Exception {
        try {
            Subject existingSubject = getById(id);
            subjectRepository.delete(existingSubject);
        } catch(NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
