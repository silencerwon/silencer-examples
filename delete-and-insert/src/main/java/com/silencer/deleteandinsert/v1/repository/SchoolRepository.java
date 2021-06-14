package com.silencer.deleteandinsert.v1.repository;

import com.silencer.deleteandinsert.v1.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Long> {

}