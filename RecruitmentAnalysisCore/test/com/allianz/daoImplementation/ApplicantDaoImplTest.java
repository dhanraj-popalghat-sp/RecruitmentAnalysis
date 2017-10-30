package com.allianz.daoImplementation;

import static org.junit.Assert.*;

import org.junit.Test;

import com.allianz.DTO.Applicant;
/*
 * This Test class of applicant, tests add applicant, get applicant and get list of applicants methods only.
 * This is not testing update and delete functionality of Applicant services.
 */
public class ApplicantDaoImplTest {

	@Test
	public void testAdd() {

		ApplicantDaoImpl daoAppDaoImpl = new ApplicantDaoImpl();
		Applicant applicant = new Applicant();
		applicant.setApplicantName("dhanaraj");
		applicant.setApplicantExp(3);
		applicant.setHighestQualification("m.tech");
		applicant.setEmail("dhanraj@allianz");
		applicant.setContact(55555);
		assertNotNull(daoAppDaoImpl.add(applicant));
	}

	@Test
	public void testDelete() {
		ApplicantDaoImpl daoAppDaoImpl = new ApplicantDaoImpl();
		Applicant applicant = new Applicant();
		assertNotNull(daoAppDaoImpl.delete(407));
	}

	@Test
	public void testUpdate() {
		ApplicantDaoImpl daoAppDaoImpl = new ApplicantDaoImpl();
		Applicant applicant = new Applicant();
		applicant.setApplicantId(401);
		applicant.setApplicantName("dhanaraj");
		assertNotNull(daoAppDaoImpl.update(applicant));
	}

	@Test
	public void testSearchByApplicantId() {
		ApplicantDaoImpl daoAppDaoImpl = new ApplicantDaoImpl();
		Applicant applicant = new Applicant();
		assertNotNull(daoAppDaoImpl.searchByApplicantId(401));
	}

	@Test
	public void testSearchAll() {
		ApplicantDaoImpl daoAppDaoImpl = new ApplicantDaoImpl();
		Applicant applicant = new Applicant();
		assertNotNull(daoAppDaoImpl.searchAll());
		
	}

}
