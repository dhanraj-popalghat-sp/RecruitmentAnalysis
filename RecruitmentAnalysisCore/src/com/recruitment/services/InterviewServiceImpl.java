/**
 * 
 */
package com.recruitment.services;

import java.util.ArrayList;
import java.util.List;

import com.recruitment.dao.InterviewDAO;
import com.recruitment.model.Interview;

/**
 * @author User
 *
 */
public class InterviewServiceImpl implements GenericServiceProvider<Interview> {

	private InterviewDAO interviewDAO = new InterviewDAO();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#add(java.lang.Object)
	 */
	public boolean addInterview(Interview interview) {
		return interviewDAO.addInterview(interview);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException();
	}

	public boolean delete(int applicantId, int postID) {
		return interviewDAO.delete(applicantId, postID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#update(java.lang.Object)
	 */
	@Override
	public Interview update(Interview interview) {
		return interviewDAO.update(interview);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieveAll()
	 */
	@Override
	public List<Interview> retrieveAll() {
		return interviewDAO.retrieveAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieve(int)
	 */
	@Override
	public Interview retrieve(int interviewID) {
		return interviewDAO.retrieve(interviewID);
	}

	public List<Interview> getshortlistedApplicant() {

		List<Interview> interviews = interviewDAO.retrieveAll();
		List<Interview> shortlistApplicant = new ArrayList<Interview>();
		for (Interview interview : interviews) {
			if (interview.isApplicantResult() == true) {
				shortlistApplicant.add(interview);
			}
		}
		return shortlistApplicant;
	}

	public List<Interview> getshortlistedApplicantByJoiningStatus() {

		List<Interview> shortlistedApplicant = getshortlistedApplicant();
		List<Interview> shortlistedApplicantByJoiningStatus = new ArrayList<Interview>();
		for (Interview interview : shortlistedApplicant) {
			if (interview.isJoiningStatus() == true) {
				shortlistedApplicantByJoiningStatus.add(interview);
			}
		}
		return shortlistedApplicantByJoiningStatus;
	}

	@Override
	public Interview add(Interview resource) {
		throw new UnsupportedOperationException();
	}
}
