package com.silencer.deleteandinsert.v3.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Getter;

@Entity
@Getter
@Table(uniqueConstraints = @UniqueConstraint(name = "idx_student_V3_unique_name", columnNames = "name"))
public class StudentV3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private SchoolV3 school;

	public static StudentV3 of(String name, SchoolV3 school) {
		StudentV3 student = new StudentV3();
		student.name = name;
		student.school = school;
		return student;
	}
}