package com.silencer.deleteandinsert.v1.service;

import com.silencer.deleteandinsert.v1.domain.School;
import com.silencer.deleteandinsert.v1.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class SchoolService {

	private final SchoolRepository schoolRepository;

	public School save(School school) {
		return schoolRepository.save(school);
	}
}
