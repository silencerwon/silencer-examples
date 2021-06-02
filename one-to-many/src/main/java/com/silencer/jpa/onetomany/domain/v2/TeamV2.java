package com.silencer.jpa.onetomany.domain.v2;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TeamV2 {

	@Column(name = "team_id", nullable = false)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@JoinColumn(name = "member_id")
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	private List<MemberV2> members;
}