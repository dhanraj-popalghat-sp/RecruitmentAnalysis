package com.allianz.daoImplementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.Date;

import org.junit.Test;

import com.allianz.DTO.HumanResource;
import com.allianz.DTO.Post;

public class PostDaoImplTest {

	Post post = null;
	PostDaoImpl postDao = null;
	HumanResource hr = null;

	@Test
	public void testAdd_post() {
		Date date = new Date(2017, 01, 01);
		Post post = new Post();
		PostDaoImpl postDao = new PostDaoImpl();
		HumanResource hr = new HumanResource();
		hr.setHrId(101);
		post.setPostName("tester");
		post.setNoOfPosts(3);
		post.setExperience(4);
		post.setHr(hr);
		post.setDateOfPost(date);
		post.setStatus(true);
		assertNotNull(postDao.add_post(post));
	}

	@Test
	public void testDelete_post() {
		Post post = new Post();
		PostDaoImpl postDao = new PostDaoImpl();
		HumanResource hr = new HumanResource();
		assertNotNull(postDao.delete_post(201));

	}

	@Test
	public void testUpdate_post() {
		Post post = new Post();
		PostDaoImpl postDao = new PostDaoImpl();
		HumanResource hr = new HumanResource();
		post.setPostId(201);
		post.setPostName("Testing");
		assertNotNull(postDao.update_post(post));
	}

	@Test
	public void testSearch_post_id() {
		Post post = new Post();
		PostDaoImpl postDao = new PostDaoImpl();
		HumanResource hr = new HumanResource();
		assertNotNull(postDao.search_post_id(201));

	}

	@Test
	public void testSearchAll_post() {
		Post post = new Post();
		PostDaoImpl postDao = new PostDaoImpl();
		HumanResource hr = new HumanResource();
		assertNotNull(postDao.searchAll_post());
	}

	@Test
	public void testSkillSetByPositionId() {
		Post post = new Post();
		PostDaoImpl postDao = new PostDaoImpl();
		HumanResource hr = new HumanResource();
		assertNotNull(postDao.skillSetByPositionId());

	}

}
