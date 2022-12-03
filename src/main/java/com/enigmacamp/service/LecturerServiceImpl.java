package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Lecturer;
import com.enigmacamp.repository.LecturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Lecturer getById(Long id) throws Exception {
        Optional<Lecturer> lecturer =lecturerRepository.findById(id);
        if(lecturer.isEmpty()) {
            throw new NotFoundException("Student " + id + " not found");
        }
        return lecturer.get();
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
    
    @Override
    public void updateLecturer(Lecturer lecturer, Long id) throws Exception {
        try {
            Lecturer existingLecturer = getById(id);
            lecturer.setLecturerId(existingLecturer.getLecturerId());
            lecturerRepository.save(lecturer);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }
    
    @Override
    public void deleteLecturer(Long id) throws Exception {
        try {
            Lecturer existingLecturer = getById(id);
            lecturerRepository.delete(existingLecturer);
        } catch(NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
