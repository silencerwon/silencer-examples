package com.silencer.deleteandinsert.v1.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.silencer.deleteandinsert.v1.domain.Student;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class StudentServiceTest {

	@Autowired
	private StudentService service;

	@DisplayName("put 연산이 재대로 동작하는지 확인")
	@Test
	void putAll() {
		// given
		service.put(Arrays.asList(Student.of(1L, "학생1")));

		// when
		service.put(Arrays.asList(Student.of(1L, "학생2"), Student.of(2L, "학생1")));

		// then
		List<Student> result = service.getAll();
		assertThat(result).size().isEqualTo(2);
		assertThat(result).extracting("name").containsExactly("학생2", "학생1");
	}
}