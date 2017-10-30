package com.allianz.daoImplementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allianz.DTO.Post;
import com.allianz.DTO.PostSkillSet;
import com.allianz.DTO.SkillSet;

public class PostSkillSetDaoImplTest {

	@Test
	public void testAddSkill() {
		Post post = new Post();
		SkillSet skillSet = new SkillSet();
		PostSkillSet postSkillSet = new PostSkillSet();
		PostSkillSetDaoImpl daoPostSkillSet = new PostSkillSetDaoImpl();
		post.setPostId(201);
		skillSet.setSkill_id(301);
		postSkillSet.setPost(post);
		postSkillSet.setSkillset(skillSet);
		assertNotNull(daoPostSkillSet.addSkill(postSkillSet));
	}

	@Test
	public void testDeleteSkill() {
		PostSkillSetDaoImpl daoPostSkillSet = new PostSkillSetDaoImpl();
		assertNotNull(daoPostSkillSet.deleteSkill(201));
	}

	@Test
	public void testUpdateSkill() {
		Post post = new Post();
		SkillSet skillSet = new SkillSet();
		PostSkillSet postSkillSet = new PostSkillSet();
		PostSkillSetDaoImpl daoPostSkillSet = new PostSkillSetDaoImpl();
		post.setPostId(201);
		skillSet.setSkill_id(301);
		postSkillSet.setPost(post);
		postSkillSet.setSkillset(skillSet);
		assertNotNull(daoPostSkillSet.updateSkill(postSkillSet));
	}

	@Test
	public void testSearchSkillByPostId() {
		PostSkillSetDaoImpl daoPostSkillSet = new PostSkillSetDaoImpl();
		assertNotNull(daoPostSkillSet.searchSkillByPostId(201));
	}

	@Test
	public void testSearchAll() {
		PostSkillSetDaoImpl daoPostSkillSet = new PostSkillSetDaoImpl();
		assertNotNull(daoPostSkillSet.searchAll());
	}

}
