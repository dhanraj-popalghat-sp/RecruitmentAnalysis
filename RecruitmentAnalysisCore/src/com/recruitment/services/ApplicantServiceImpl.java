/**
 * 
 */
package com.recruitment.services;

import java.util.List;

import com.recruitment.dao.ApplicantDAO;
import com.recruitment.model.Applicant;

/**
 * 
 * {@ ApplicantServiceImpl} service implementation provides functionality over
 * <i>ApplicantDAO</i>
 *
 */
public class ApplicantServiceImpl implements GenericServiceProvider<Applicant> {

	ApplicantDAO applicantDAO = new ApplicantDAO();

	/*
	 * 
	 * 
	 * @param applicant Takes applicant object from User
	 * 
	 * @return applicant added applicant object
	 */
	@Override
	public Applicant add(Applicant applicant) {
		return applicantDAO.add(applicant);
	}

	/*
	 * @param applicantId Takes applicant Id which want to delete, as an Integer
	 * 
	 * @return applicantId Deleted applicantId
	 */
	@Override
	public boolean delete(int applicantId) {
		return applicantDAO.delete(applicantId);
	}

	/*
	 * @param applicant Takes applicant object as the user wants to update like
	 * applicant name, applicant experience
	 * 
	 * @return applicant updated applicant object
	 */
	@Override
	public Applicant update(Applicant applicant) {
		return applicantDAO.update(applicant);
	}

	/*
	 * @ return List of applicant objects
	 */
	@Override
	public List<Applicant> retrieveAll() {
		return applicantDAO.retrieveAll();
	}

	/*
	 * @ param applicant_Id Takes an integer
	 * 
	 * @ return
	 */

	@Override
	public Applicant retrieve(int applicantId) {
		return applicantDAO.retrieve(applicantId);
	}

}
