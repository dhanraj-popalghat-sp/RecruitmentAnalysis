package com.allianz.daoImplementation;

import static org.junit.Assert.assertNotNull;

import java.sql.Date;

import org.junit.Test;

import com.recruitment.model.Applicant;

public class ApplicantApplyPostDaoImplTest {

	@Test
	public void testAddApplication() {
		Post post = new Post();
		Applicant applicant = new Applicant();
		ApplicantApply applicantApply = new ApplicantApply();
		ApplicantApplyPostDaoImpl daoApplicantApplyPost = new ApplicantApplyPostDaoImpl();
		Date date = new Date(2017, 01, 10);
		post.setPostId(201);
		applicant.setApplicantId(401);
		applicantApply.setApplicant(applicant);
		applicantApply.setPost(post);
		applicantApply.setDateOfApply(date);
		assertNotNull(daoApplicantApplyPost.addApplication(applicantApply));
	}

	@Test
	public void testDeleteApplication() {
		Post post = new Post();
		Applicant applicant = new Applicant();
		ApplicantApply applicantApply = new ApplicantApply();
		ApplicantApplyPostDaoImpl daoApplicantApplyPost = new ApplicantApplyPostDaoImpl();

		assertNotNull(daoApplicantApplyPost.deleteApplication(401, 201));
	}

	@Test
	public void testUpdate() {

	}

	@Test
	public void testSearchApplicant() {
		ApplicantApplyPostDaoImpl daoApplicantApplyPost = new ApplicantApplyPostDaoImpl();
		assertNotNull(daoApplicantApplyPost.searchAllApplications());
	}

	@Test
	public void testSearchAllApplications() {
		ApplicantApplyPostDaoImpl daoApplicantApplyPost = new ApplicantApplyPostDaoImpl();
		assertNotNull(daoApplicantApplyPost.searchAllApplications());
	}

}
