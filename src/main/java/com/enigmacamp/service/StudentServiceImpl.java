package com.enigmacamp.service;

import com.enigmacamp.exception.EntityExistException;
import com.enigmacamp.exception.NotFoundException;
import com.enigmacamp.model.Student;
import com.enigmacamp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
    public Student getById(Long id) throws Exception {
        Optional<Student> student =studentRepository.findById(id);
        if(student.isEmpty()) {
            throw new NotFoundException("Student " + id + " not found");
        }
        return student.get();
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
    
    @Override
    public void updateStudent(Student student, Long id) throws Exception {
        try {
            Student existingStudent = getById(id);
            student.setStudentId(existingStudent.getStudentId());
            studentRepository.save(student);
        } catch(NotFoundException e) {
            throw new NotFoundException("Update failed because ID is not found");
        }
    }
    
    @Override
    public void deleteStudent(Long id) throws Exception {
        try {
            Student existingStudent = getById(id);
            studentRepository.delete(existingStudent);
        } catch(NotFoundException e) {
            throw new NotFoundException("Delete failed because ID is not found");
        }
    }
}
