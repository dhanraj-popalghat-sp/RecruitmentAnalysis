package com.allianz.daoImplementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allianz.DTO.SkillSet;

public class SkillSetDaoImplTest {

	@Test
	public void testAddSkill() {
		SkillSet skillset = new SkillSet();
		SkillSetDaoImpl daoSkillSetDaoImpl = new SkillSetDaoImpl();
		skillset.setSkill_id(307);
		skillset.setSkill_name("advance Java");
		assertNotNull(daoSkillSetDaoImpl.addSkill(skillset));

	}

	@Test
	public void testDeleteSkill() {
		SkillSet skillset = new SkillSet();
		SkillSetDaoImpl daoSkillSetDaoImpl = new SkillSetDaoImpl();
		assertNotNull(daoSkillSetDaoImpl.deleteSkill(307));

	}

	@Test
	public void testUpdateSkill() {
		SkillSet skillset = new SkillSet();
		SkillSetDaoImpl daoSkillSetDaoImpl = new SkillSetDaoImpl();
		skillset.setSkill_id(307);
		skillset.setSkill_name("PHP");
		assertNotNull(daoSkillSetDaoImpl.updateSkill(skillset));

	}

	@Test
	public void testSearchById()
	{
		SkillSet skillset = new SkillSet();
		SkillSetDaoImpl daoSkillSetDaoImpl = new SkillSetDaoImpl();
		assertNotNull(daoSkillSetDaoImpl.searchById(307));
	}

	@Test
	public void testSearchAll() {
		SkillSet skillset = new SkillSet();
		SkillSetDaoImpl daoSkillSetDaoImpl = new SkillSetDaoImpl();
		assertNotNull(daoSkillSetDaoImpl.searchAll());

	
	}

}
