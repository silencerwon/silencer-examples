package com.silencer.deleteandinsert.v1.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Student {

	@Id
	private Long id;

	private String name;

	public static Student of(long id, String name) {
		Student student = new Student();
		student.id = id;
		student.name = name;
		return student;
	}
}