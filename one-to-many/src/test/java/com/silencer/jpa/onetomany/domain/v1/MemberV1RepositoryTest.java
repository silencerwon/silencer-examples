package com.silencer.jpa.onetomany.domain.v1;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;
import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class MemberV1RepositoryTest {

	@Autowired
	private TeamV1Repository teamV1Repository;

	@Autowired
	private MemberV1Repository memberV1Repository;

	@Autowired
	private EntityManager em;

	private Long currentTeamId;
	private Long currentMember1Id;
	private Long currentMember2Id;
	private Long currentMember3Id;

	@BeforeEach
	void setUp() {
		log.info("\n\n===== Start: insert initial data =====\n\n");
		TeamV1 team = TeamV1.of("레알마드리드");
		team = teamV1Repository.save(team);
		currentTeamId = team.getId();

		MemberV1 member1 = memberV1Repository.save(MemberV1.of("테스트1번", 25));
		MemberV1 member2 = memberV1Repository.save(MemberV1.of("테스트2번", 26));
		MemberV1 member3 = memberV1Repository.save(MemberV1.of("테스트3번", 27));

		currentMember1Id = member1.getId();
		currentMember2Id = member2.getId();
		currentMember3Id = member3.getId();

		team.getMembers().addAll(Arrays.asList(member1, member2, member3));

		em.flush();
		em.clear();

		log.info("\n\n===== Finish: insert initial data =====\n\n");
	}

	@AfterEach
	void tearDown() {
		log.info("\n\n===== Start: delete initial data =====\n\n");
		teamV1Repository.deleteAll();
		memberV1Repository.deleteAll();
		log.info("\n\n===== Finish: delete initial data =====\n\n");
	}

	// insert시에는 cascade옵션이 있어야 반영된다.
	@Test
	void insert() {
		// given
		TeamV1 team = teamV1Repository.findById(currentTeamId).orElseThrow();

		// when
		team.getMembers().add(MemberV1.of("테스트4번", 28));
		em.flush();
		em.clear();

		// then
		TeamV1 result = teamV1Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isEqualTo(4);
	}

	// insert시에는 cascade옵션이 있어야 반영된다.
	@Test
	void delete() {
		// given
		TeamV1 team = teamV1Repository.findById(currentTeamId).orElseThrow();

		// when
		team.getMembers().remove(0);
		em.flush();
		em.clear();

		// then
		TeamV1 result = teamV1Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isEqualTo(2);
		List<MemberV1> members = memberV1Repository.findAll();
		assertThat(members).size().isEqualTo(2);
	}
}