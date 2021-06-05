package com.silencer.jpa.onetomany.domain.v1;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class MemberV1 {

	@Column(name = "member_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer age;

	public static MemberV1 of(String name, int age) {
		MemberV1 member = new MemberV1();
		member.name = name;
		member.age = age;
		return member;
	}
}