package com.silencer.deleteandinsert.v2.service;

import com.silencer.deleteandinsert.v2.domain.SchoolV2;
import com.silencer.deleteandinsert.v2.domain.StudentV2;
import com.silencer.deleteandinsert.v2.repository.SchoolRepositoryV2;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolServiceV2 {

	private final SchoolRepositoryV2 schoolRepositoryV2;

	private final EntityManager entityManager;

	public SchoolV2 save(SchoolV2 school) {
		return schoolRepositoryV2.save(school);
	}

	@Transactional
	public SchoolV2 replaceStudents(Long schoolId, List<String> studentNames) {
		SchoolV2 school = schoolRepositoryV2.findById(schoolId).orElseThrow();
		school.getStudents().clear();

		entityManager.flush();
		entityManager.clear();

		final SchoolV2 schoolAfterDeleteAll = schoolRepositoryV2.findById(schoolId).orElseThrow();
		schoolAfterDeleteAll.getStudents().addAll(studentNames.stream().map(name -> StudentV2.of(name, schoolAfterDeleteAll)).collect(Collectors.toList()));

		return schoolAfterDeleteAll;
	}
}
