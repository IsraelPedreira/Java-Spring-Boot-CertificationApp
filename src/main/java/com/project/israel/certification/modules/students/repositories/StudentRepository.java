package com.project.israel.certification.modules.students.repositories;

import com.project.israel.certification.modules.students.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StudentRepository extends JpaRepository<StudentEntity, UUID>{
    public Optional<StudentEntity> findByEmail(String email);
}
