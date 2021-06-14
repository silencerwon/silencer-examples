package com.silencer.deleteandinsert.v3.service;

import com.silencer.deleteandinsert.v3.domain.SchoolV3;
import com.silencer.deleteandinsert.v3.domain.StudentV3;
import com.silencer.deleteandinsert.v3.repository.SchoolRepositoryV3;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolServiceV3 {

	private final SchoolRepositoryV3 schoolRepositoryV3;

	private final EntityManager entityManager;

	public SchoolV3 save(SchoolV3 school) {
		return schoolRepositoryV3.save(school);
	}

	@Transactional
	public SchoolV3 replaceStudents(Long schoolId, List<StudentV3> students) {
		SchoolV3 school = schoolRepositoryV3.findById(schoolId).orElseThrow();
		List<StudentV3> currentStudents = school.getStudents();

		// 갱신할 목록에 없는 값 제거
		Set<String> studentNames = students.stream().map(StudentV3::getName).collect(Collectors.toSet());
		currentStudents.removeIf(it -> !studentNames.contains(it.getName()));

		// 갱신할 목록에만 있는 값 추가
		Set<String> currentStudentsNames = currentStudents.stream().map(StudentV3::getName).collect(Collectors.toSet());
		currentStudents.addAll(
			students.stream()
				.filter(it -> !currentStudentsNames.contains(it.getName()))
				.collect(Collectors.toList())
		);

		return school;
	}
}
