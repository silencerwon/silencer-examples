package com.silencer.deleteandinsert.v2.domain;

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
public class SchoolV2 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentV2> students = new ArrayList<>();

	public static SchoolV2 of(String name) {
		SchoolV2 school = new SchoolV2();
		school.name = name;
		return school;
	}

	public void replaceStudents(List<StudentV2> studentV2s) {
		this.students.clear();

		this.students.addAll(studentV2s);
	}
}