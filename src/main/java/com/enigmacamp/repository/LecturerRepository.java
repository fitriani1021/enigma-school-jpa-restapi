package com.enigmacamp.repository;

import com.enigmacamp.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturerRepository extends JpaRepository<Lecturer, Long > {
}
