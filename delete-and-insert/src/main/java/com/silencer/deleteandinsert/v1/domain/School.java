package com.silencer.deleteandinsert.v1.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;

@Table(name = "SCHOOL")
@Getter
@Entity
public class School {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(mappedBy = "school", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Student> students = new ArrayList<>();

	public static School of(String name) {
		School school = new School();
		school.name = name;
		return school;
	}

	public void replaceStudents(List<Student> students) {
		this.students.clear();

		this.students.addAll(students);
	}
}