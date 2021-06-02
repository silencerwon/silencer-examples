package com.silencer.jpa.onetomany.domain.v3;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberV3Test {

	@Autowired
	private MemberV3Repository memberV3Repository;

	@Autowired
	private TeamV3Repository teamV3Repository;

	@Autowired
	private EntityManager em;

	@BeforeEach
	void setUp() {

	}

	@DisplayName("테스트")
	@Test
	void name1() {
		// given
		TeamV3 team1 = TeamV3.of(1L, "레알마드리드");
		teamV3Repository.save(team1);

		MemberV3 member1 = MemberV3.of(1L, "테스트1번", 25, team1);
		MemberV3 member2 = MemberV3.of(2L, "테스트2번", 26, team1);
		MemberV3 member3 = MemberV3.of(3L, "테스트3번", 27, team1);
		memberV3Repository.saveAll(Arrays.asList(member1, member2, member3));

		em.flush();
		em.clear();
		System.out.println("===== 1. Finish - add team and members");

		// when
		Optional<TeamV3> optionalTeam = teamV3Repository.findById(1L);

		// then
		assertThat(optionalTeam)
			.isNotEmpty()
			.get()
			.extracting("members")
			.asList()
			.size().isEqualTo(3);

		System.out.println("===== 2. find - add team and members");
		System.out.println("===== 2. find - add team and members");

		// 1을 지움
		optionalTeam.get()
			.getMembers()
			.removeIf(memberV3 -> memberV3.getId() == 1L);

		em.flush();
		em.clear();
		System.out.println("=====");

		Optional<TeamV3> optionalTeam2 = teamV3Repository.findById(1L);

		// then
		assertThat(optionalTeam2)
			.isNotEmpty()
			.get()
			.extracting("members")
			.asList()
			.size().isEqualTo(2);

	}
}