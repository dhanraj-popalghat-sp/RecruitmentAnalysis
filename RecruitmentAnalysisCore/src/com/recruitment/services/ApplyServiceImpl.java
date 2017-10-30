/**
 * 
 */
package com.recruitment.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.recruitment.dao.ApplyDAO;
import com.recruitment.model.Applicant;
import com.recruitment.model.Apply;
import com.recruitment.model.Position;

/**
 * @author User
 *
 */
public class ApplyServiceImpl implements GenericServiceProvider<Apply> {

	ApplyDAO applyDAO = new ApplyDAO();
	ApplicantServiceImpl applicantServiceImpl = new ApplicantServiceImpl();
	PositionServiceImpl positionService = new PositionServiceImpl();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#add(java.lang.Object)
	 */
	public boolean addApply(Apply apply) {
		return applyDAO.addApply(apply);
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
		return applyDAO.delete(applicantId, postID);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#update(java.lang.Object)
	 */
	@Override
	public Apply update(Apply resource) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieveAll()
	 */
	@Override
	public List<Apply> retrieveAll() {
		return applyDAO.retrieveAll();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.services.GenericServiceProvider#retrieve(int)
	 */
	@Override
	public Apply retrieve(int id) {
		throw new UnsupportedOperationException();
	}

	public Apply retrieve(int applicantID, int postID) {
		return applyDAO.retrieve(applicantID, postID);
	}

	public void checkApplicantCriteria(Applicant applicant, int positionId) {
		List<Applicant> applicants = applicantServiceImpl.retrieveAll();

		if (applicants.isEmpty()) {
			System.out.println("isEmpty()");
			applicantServiceImpl.add(applicant);
		}

		List<Applicant> applicants2 = applicantServiceImpl.retrieveAll();

		boolean status = false;

		for (Applicant applicant2 : applicants2) {
			System.out.println(applicant2.getName());
			System.out.println("recieved-->" + applicant.getName());
			if (applicant2.getName().equalsIgnoreCase(applicant.getName())) {
				int applicantId = applicant2.getApplicantId();
				checkExperience(applicantId, positionId);
				break;
			} else {
				status = true;
			}
		}

		if (status) {
			System.out.println("In ELSE");
			applicantServiceImpl.add(applicant); // Add Applicant after
													// clicking on submit
													// button

			int applicantId = 0;
			List<Applicant> applicants3 = applicantServiceImpl.retrieveAll();
			for (Applicant applicant2 : applicants3) {
				applicantId = applicant2.getApplicantId();
			}

			checkExperience(applicantId, positionId);
		}

	}

	public void checkExperience(int applicantId, int positionId) {
		System.out.println(applicantId);
		Applicant applicantFinal = applicantServiceImpl.retrieve(applicantId);

		System.out.println(applicantFinal);
		double applicantExperience = applicantFinal.getExperience();
		System.out.println(applicantExperience);

		Position position = positionService.retrieve(positionId);
		double positionExperience = position.getExperience();
		System.out.println(positionExperience);

		if (applicantExperience >= positionExperience) {
			applyApplicant(applicantFinal, positionId);
		} else {
			try {
				throw new Exception(
						"Your experience didnot not match to required experience\n Can not apply for this position");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void applyApplicant(Applicant applicant, int positionId) {

		System.out.println(applicant);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String format = dateFormat.format(date);

		Date finalDate = null;
		java.sql.Date sqlFinalDate = null;
		try { // To get Current Date and
			finalDate = dateFormat.parse(format); // store it in database.
			sqlFinalDate = new java.sql.Date(finalDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}

		PositionServiceImpl positionServiceImpl = new PositionServiceImpl();
		Position position = positionServiceImpl.retrieve(positionId);

		Apply apply = new Apply();
		apply.setApplicant(applicant);
		apply.setPost(position);
		apply.setDateOfApply(sqlFinalDate);

		ApplyServiceImpl applyServiceImpl = new ApplyServiceImpl();
		applyServiceImpl.add(apply);
	}

	@Override
	public Apply add(Apply resource) {
		throw new UnsupportedOperationException();
	}
}
