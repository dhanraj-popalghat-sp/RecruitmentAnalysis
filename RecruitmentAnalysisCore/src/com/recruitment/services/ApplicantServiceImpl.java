/**
 * 
 */
package com.recruitment.services;

import java.util.List;

import com.recruitment.dao.ApplicantDAO;
import com.recruitment.model.Applicant;

/**
 * @author User
 *
 */
public class ApplicantServiceImpl implements GenericServiceProvider<Applicant> {

	ApplicantDAO applicantDAO = new ApplicantDAO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.recruitment.services.GenericServiceProvider#add(java.lang.Object)
	 */
	@Override
	public Applicant add(Applicant applicant) {
		return applicantDAO.add(applicant);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#delete(int)
	 */
	@Override
	public boolean delete(int applicantId) {
		return applicantDAO.delete(applicantId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.recruitment.services.GenericServiceProvider#update(java.lang.Object)
	 */
	@Override
	public Applicant update(Applicant applicant) {
		return applicantDAO.update(applicant);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieveAll()
	 */
	@Override
	public List<Applicant> retrieveAll() {
		return applicantDAO.retrieveAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieve(int)
	 */
	@Override
	public Applicant retrieve(int applicantId) {
		return applicantDAO.retrieve(applicantId);
	}

}
