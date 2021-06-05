package com.silencer.jpa.onetomany.domain.v3;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = "team")
public class MemberV3 {

	@Id
	@Column(name = "member_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	private Integer age;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "team_id")
	private TeamV3 team;

	public static MemberV3 of(String name, Integer age) {
		MemberV3 member = new MemberV3();
		member.name = name;
		member.age = age;
		member.team = null;
		return member;
	}

	public static MemberV3 of(String name, Integer age, TeamV3 team) {
		MemberV3 member = new MemberV3();
		member.name = name;
		member.age = age;
		member.team = team;
		return member;
	}
}