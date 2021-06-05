package com.silencer.jpa.onetomany.domain.v1;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamV1 {

	@Column(name = "team_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MemberV1> members = new ArrayList<>();

	public static TeamV1 of(String name) {
		TeamV1 team = new TeamV1();
		team.name = name;
		return team;
	}
}