package com.enigmacamp.repository;

import com.enigmacamp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, String > {
}
