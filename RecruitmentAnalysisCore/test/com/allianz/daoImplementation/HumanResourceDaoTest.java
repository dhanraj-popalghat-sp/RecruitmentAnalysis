package com.allianz.daoImplementation;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.allianz.DTO.HumanResource;
import com.allianz.daoImplementation.HrDaoImpl;

public class HumanResourceDaoTest {

	@Test
	public void testAdd() {

		HumanResource hr = null;
		HrDaoImpl daoHr = null;
		daoHr = new HrDaoImpl();
		hr = new HumanResource();
		hr.setHrName("joy");
		hr.setHrContact(333333);
		hr.setHrDept("Experiance");
		hr.setPassword("joy@allianz");
		assertNotNull(daoHr.add(hr));
	}

	@Test
	public void testDelete() {
	}

	@Test
	public void testUpdate_Hr() {
		HumanResource hr = new HumanResource();
		HrDaoImpl daoHr = new HrDaoImpl();
		hr.setHrId(101);
		hr.setHrName("Parthiv");
		assertNotNull(daoHr.update_Hr(hr));
	}

	@Test
	public void testSearchBy_hr_id() {
		HumanResource hr = new HumanResource();
		HrDaoImpl daoHr = new HrDaoImpl();
		assertNotNull(daoHr.searchBy_hr_id(101));
	}

	@Test
	public void testSearchAll() {
		HumanResource hr = new HumanResource();
		HrDaoImpl daoHr = new HrDaoImpl();
		assertNotNull(daoHr.searchAll());
	}

}
