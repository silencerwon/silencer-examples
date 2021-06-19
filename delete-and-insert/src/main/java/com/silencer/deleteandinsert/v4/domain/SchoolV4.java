package com.silencer.deleteandinsert.v4.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
public class SchoolV4 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Setter
	@ElementCollection(targetClass = StudentV4.class)
	@CollectionTable(name = "STUDENT_V4")
	private List<StudentV4> students = new ArrayList<>();

	public static SchoolV4 of(String name) {
		SchoolV4 school = new SchoolV4();
		school.name = name;
		return school;
	}
}