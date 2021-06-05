package com.silencer.jpa.onetomany.domain.v2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class MemberV2 {

	@Column(name = "member_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer age;

	public static MemberV2 of(String name, int age) {
		MemberV2 member = new MemberV2();
		member.name = name;
		member.age = age;
		return member;
	}
}