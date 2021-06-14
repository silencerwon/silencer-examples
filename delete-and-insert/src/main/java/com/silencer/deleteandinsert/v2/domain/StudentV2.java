package com.silencer.deleteandinsert.v2.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(name = "idx_student_v2_unique_name", columnNames = "name"))
public class StudentV2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	private SchoolV2 school;

	public static StudentV2 of(String name, SchoolV2 school) {
		StudentV2 studentV2 = new StudentV2();
		studentV2.name = name;
		studentV2.school = school;
		return studentV2;
	}
}