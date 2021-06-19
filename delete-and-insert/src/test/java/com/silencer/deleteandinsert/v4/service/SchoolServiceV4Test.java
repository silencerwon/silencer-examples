package com.silencer.deleteandinsert.v4.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.silencer.deleteandinsert.v4.domain.SchoolV4;
import com.silencer.deleteandinsert.v4.domain.StudentV4;
import com.silencer.deleteandinsert.v4.repository.SchoolRepositoryV4;
import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class SchoolServiceV4Test {

	public static final String STUDENT1_NAME = "학생1";
	public static final String STUDENT2_NAME = "학생2";
	public static final String STUDENT3_NAME = "학생3";
	public static final String SCHOOL_NAME = "테스트학교";

	@Autowired
	private SchoolServiceV4 service;

	@Autowired
	private SchoolRepositoryV4 repository;

	@Autowired
	private EntityManager em;

	@DisplayName("학생을 교체(deleteAll, insert)할 수 있는지 확인")
	@Test
	@Transactional
	@Rollback(false)
	void replaceStudents() {
		// given (학교에 학생1, 학생2를 저장 - 학생명에 unique constraint가 걸려 있음)
		SchoolV4 school = SchoolV4.of(SCHOOL_NAME);
		school.setStudents(Arrays.asList(StudentV4.of(STUDENT1_NAME), StudentV4.of(STUDENT2_NAME)));
		school = service.save(school);
		Long schoolId = school.getId();
		em.flush();
		em.clear();

		// when (학생1, 학생3으로 변경)
		school = repository.findById(schoolId).orElseThrow();
		school.setStudents(Arrays.asList(StudentV4.of(STUDENT1_NAME), StudentV4.of(STUDENT3_NAME)));
		em.flush();
		em.clear();

		// then
		school = repository.findById(schoolId).orElseThrow();
		List<StudentV4> students = school.getStudents();
		assertThat(students).size().isEqualTo(2);
		assertThat(students).extracting("name").containsExactly(STUDENT1_NAME, STUDENT3_NAME);
	}
}