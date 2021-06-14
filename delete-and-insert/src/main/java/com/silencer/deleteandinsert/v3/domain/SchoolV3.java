package com.silencer.deleteandinsert.v3.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class SchoolV3 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentV3> students = new ArrayList<>();

	public static SchoolV3 of(String name) {
		SchoolV3 school = new SchoolV3();
		school.name = name;
		return school;
	}

	public void replaceStudents(List<StudentV3> studentV3s) {
		this.students.clear();

		this.students.addAll(studentV3s);
	}
}