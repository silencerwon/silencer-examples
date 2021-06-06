package com.silencer.deleteandinsert.v1.repository;

import com.silencer.deleteandinsert.v1.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {

}