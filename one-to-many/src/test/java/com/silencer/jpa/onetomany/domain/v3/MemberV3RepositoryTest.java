package com.silencer.jpa.onetomany.domain.v3;

import static org.assertj.core.api.Assertions.assertThat;

import javax.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@SpringBootTest
@Transactional
class MemberV3RepositoryTest {

	@Autowired
	private MemberV3Repository memberV3Repository;

	@Autowired
	private TeamV3Repository teamV3Repository;

	@Autowired
	private EntityManager em;

	private Long currentTeamId;

	@BeforeEach
	void setUp() {
		log.info("\n\n===== Start: insert initial data =====\n\n");
		TeamV3 team = TeamV3.of("레알마드리드");
		team = teamV3Repository.save(team);
		currentTeamId = team.getId();

		MemberV3 member1 = MemberV3.of("테스트1번", 25, team);
		MemberV3 member2 = MemberV3.of("테스트2번", 26, team);
		MemberV3 member3 = MemberV3.of("테스트3번", 27, team);

		team.getMembers().add(member1);
		team.getMembers().add(member2);
		team.getMembers().add(member3);

		em.flush();
		em.clear();

		log.info("\n\n===== Finish: insert initial data =====\n\n");
	}

	@AfterEach
	void tearDown() {
		log.info("\n\n===== Start: delete initial data =====\n\n");
		memberV3Repository.deleteAll();
		teamV3Repository.deleteAll();
		log.info("\n\n===== Finish: delete initial data =====\n\n");
	}

	@DisplayName("양방향 관계에서 members(@OneToMany쪽)에 멤버를 교체해도 DB에 반영됨")
	@Test
	void modifyMembers() {
		// given
		TeamV3 team = teamV3Repository.findById(currentTeamId).orElseThrow();

		// when
		team.getMembers().removeAll(team.getMembers());
		MemberV3 member = MemberV3.of("테스트4번", 28, team);
		team.getMembers().addAll(Lists.newArrayList(member));

		em.flush();
		em.clear();

		// then
		TeamV3 result = teamV3Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isOne();
	}

	@DisplayName("양방향 관계에서 members(@OneToMany쪽)에 새로운 멤버를 삽입해도 DB에 반영됨")
	@Test
	void addMembers() {
		// given
		TeamV3 team = teamV3Repository.findById(currentTeamId).orElseThrow();

		// when
		MemberV3 member = MemberV3.of("테스트4번", 28, team);
		team.getMembers().addAll(Lists.newArrayList(member));

		em.flush();
		em.clear();

		// then
		TeamV3 result = teamV3Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isEqualTo(4);
	}

	@DisplayName("양방향 관계에서 @OneToMany쪽에 기존 멤버를 삭제하면 DB에 반영")
	@Test
	void deleteMember() {
		// given
		TeamV3 team = teamV3Repository.findById(currentTeamId).orElseThrow();

		// when
		team.getMembers().remove(0);
		em.flush();
		em.clear();

		// then
		TeamV3 result = teamV3Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isEqualTo(2);
	}

	@DisplayName("양방향 관계에서 @OneToMany쪽에 기존 멤버를 모두 삭제하면 DB에 반영")
	@Test
	void deleteAllMembers() {
		// given
		TeamV3 team = teamV3Repository.findById(currentTeamId).orElseThrow();

		// when
		log.info("team1's member size: {}", team.getMembers().size());
		team.getMembers().clear();
		em.flush();
		em.clear();

		// then
		TeamV3 result = teamV3Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isZero();
	}

	@DisplayName("MemberRepository로도 저장은 가능하나, team의 members에는 추가된 멤버가 반영되지 않는다.")
	@Test
	void addMemberByMemberRepository() {
		// given
		TeamV3 team = teamV3Repository.findById(currentTeamId).orElseThrow();
		System.out.println(team.getMembers().size()); // load lazy field

		// when
		MemberV3 member = MemberV3.of("테스트4번", 28, team);
		memberV3Repository.save(member);

		// then
		assertThat(team.getMembers()).size().isEqualTo(3);
		em.flush();
		em.clear();
		TeamV3 result = teamV3Repository.findById(currentTeamId).orElseThrow();
		assertThat(result.getMembers()).size().isEqualTo(4);
	}
}