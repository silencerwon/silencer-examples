package com.silencer.deleteandinsert.v1.service;

import com.silencer.deleteandinsert.v1.domain.Student;
import com.silencer.deleteandinsert.v1.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public List<Student> getAll() {
		return studentRepository.findAll();
	}

	@Transactional
	public void put(List<Student> students) {
		studentRepository.deleteAll();
		studentRepository.flush();

		studentRepository.saveAll(students);
	}
}
