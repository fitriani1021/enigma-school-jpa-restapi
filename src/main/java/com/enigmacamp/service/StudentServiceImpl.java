package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.model.Student;
import com.enigmacamp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public Page<Student> listStudent(Integer page, Integer pageSize, String sortDirection, String orderBy) throws Exception {
        Sort sort = Sort.by(Sort.Direction.valueOf(sortDirection), orderBy);
        Pageable pageable = PageRequest.of((page - 1), pageSize, sort);
        Page<Student> result = studentRepository.findAll(pageable);
        return result;
    }
    
    @Override
    public Student createStudent(Student student) {
        try {
            Student newStudent = studentRepository.save(student);
            return newStudent;
        }catch(DataIntegrityViolationException e){
            throw new EntityExistException();
        }
    }
}
