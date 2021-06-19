package com.silencer.deleteandinsert.v4.service;

import com.silencer.deleteandinsert.v3.domain.StudentV3;
import com.silencer.deleteandinsert.v4.domain.SchoolV4;
import com.silencer.deleteandinsert.v4.repository.SchoolRepositoryV4;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolServiceV4 {

	private final SchoolRepositoryV4 schoolRepositoryV4;

	private final EntityManager entityManager;

	public SchoolV4 save(SchoolV4 school) {
		return schoolRepositoryV4.save(school);
	}

	@Transactional
	public SchoolV4 replaceStudents(Long schoolId, List<StudentV3> students) {
		SchoolV4 school = schoolRepositoryV4.findById(schoolId).orElseThrow();
		return school;
	}
}
