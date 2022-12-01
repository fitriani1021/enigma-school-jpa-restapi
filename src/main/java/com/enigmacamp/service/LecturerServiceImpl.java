package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.model.Lecturer;
import com.enigmacamp.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class LecturerServiceImpl implements LecturerService{
    @Autowired
    private LecturerRepository lecturerRepository;
    
    @Override
    public Page<Lecturer> listLecturer(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        Page<Lecturer> result = lecturerRepository.findAll(pageable);
        return result;
    }
    
    @Override
    public Lecturer createLecturer(Lecturer lecturer) throws Exception {
        try {
            Lecturer newLecturer = lecturerRepository.save(lecturer);
            return newLecturer;
        }catch(DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }
}
