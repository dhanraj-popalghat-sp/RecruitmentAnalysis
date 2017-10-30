package com.recruitment.dao;

import static com.recruitment.dao.util.DBConstants.COL_APPLICANT_ID;
import static com.recruitment.dao.util.DBConstants.COL_APPLICANT_NAME;
import static com.recruitment.dao.util.DBConstants.COL_CONTACT;
import static com.recruitment.dao.util.DBConstants.COL_EMAIL;
import static com.recruitment.dao.util.DBConstants.COL_EXPERIENCE;
import static com.recruitment.dao.util.DBConstants.COL_HIGHEST_QUALIFICATION;
import static com.recruitment.dao.util.DBConstants.TBL_APPLICANT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.db.DBConnection;
import com.recruitment.model.Applicant;

/**
 * 
 * {@code ApplicantDAO} provides multiple functionalities to interact with
 * database. It provides methods for adding,deleting,updating and searching
 * {@link Applicant}
 * 
 * @author Dhanraj Popalghat
 * @author Akash Mithari
 * @author Harshada Harde
 *
 */
public class ApplicantDAO implements GenericDAOContract<Applicant> {

	// All Applicant database queries
	private String selectApplicantQuery = "SELECT * FROM {0} WHERE {1}=?";
	private String insertApplicantQuery = "INSERT INTO {0} ({1},{2},{3},{4},{5}) VALUES (?,?,?,?,?)";
	private String selectAllApplicantQuery = "SELECT * FROM {0}";
	private String updateApplicantQuery = "UPDATE {0} SET {1}=?,{2}=?,{3}=?,{4}=?,{5}=? WHERE {6}=?";
	private String deleteApplicant = "DELETE FROM {0} WHERE {1}=?";

	private static Logger logger = Logger.getLogger(PositionDAO.class);
	private PreparedStatement statement;
	private ResultSet resultSet;

	@Override
	/**
	 * Add Applicant by applicantId
	 * 
	 */
	public Applicant add(Applicant applicant) {
		logger.info(applicant.getName());

		try {
			MessageFormat messageFormat = new MessageFormat(insertApplicantQuery);
			insertApplicantQuery = messageFormat.format(new Object[] { TBL_APPLICANT, COL_APPLICANT_NAME,
					COL_EXPERIENCE, COL_HIGHEST_QUALIFICATION, COL_EMAIL, COL_CONTACT });

			statement = DBConnection.getStatement(insertApplicantQuery);

			statement.setString(1, applicant.getName());
			statement.setDouble(2, applicant.getExperience());
			statement.setString(3, applicant.getHighest_qual());
			statement.setString(4, applicant.getEmail());
			statement.setString(5, applicant.getContact());

			int check = statement.executeUpdate();

			logger.debug("applicant inserted : " + check);
			if (check > 0) {
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					System.out.println(generatedKeys.getInt(1));
					applicant.setApplicantId(generatedKeys.getInt(1));
				}
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return applicant;
	}

	@Override
	/**
	 * Delete applicant by applicantId
	 * 
	 */
	public boolean delete(int applicantID) {
		try {
			MessageFormat messageFormat = new MessageFormat(deleteApplicant);
			deleteApplicant = messageFormat.format(new Object[] { TBL_APPLICANT, COL_APPLICANT_ID });
			statement = DBConnection.getStatement(deleteApplicant);
			statement.setInt(1, applicantID);
			int check = statement.executeUpdate();
			logger.info("Applicant deleted with id : " + applicantID);
			if (check > 0)
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	/**
	 * Update Applicant
	 * 
	 */
	public Applicant update(Applicant applicant) {
		Applicant updatedApplicant = null;
		try {
			MessageFormat messageFormat = new MessageFormat(updateApplicantQuery);
			updateApplicantQuery = messageFormat.format(new Object[] { TBL_APPLICANT, COL_APPLICANT_NAME,
					COL_EXPERIENCE, COL_HIGHEST_QUALIFICATION, COL_EMAIL, COL_CONTACT, COL_APPLICANT_ID });

			logger.info("Update query for applicant : " + updateApplicantQuery);

			// Position object from database
			Applicant applicantInDB = new ApplicantDAO().retrieve(applicant.getApplicantId());
			statement = DBConnection.getStatement(updateApplicantQuery);

			if (applicant.getName() == null)
				applicant.setName(applicantInDB.getName());

			if (applicant.getExperience() == 0)
				applicant.setExperience(applicantInDB.getExperience());

			if (applicant.getHighest_qual() == null)
				applicant.setHighest_qual(applicantInDB.getHighest_qual());

			if (applicant.getEmail() == null) {
				applicant.setEmail(applicantInDB.getEmail());
			}

			if (applicant.getContact() == null) {
				applicant.setContact(applicantInDB.getContact());
			}

			statement.setString(1, applicant.getName());
			statement.setDouble(2, applicant.getExperience());
			statement.setString(3, applicant.getHighest_qual());
			statement.setString(4, applicant.getEmail());
			statement.setString(5, applicant.getContact());
			statement.setInt(6, applicant.getApplicantId());

			int check = statement.executeUpdate();
			if (check > 0)
				logger.debug("Applicant updated with id : " + applicant.getApplicantId());
			else
				logger.debug("failed to update HR");
			updatedApplicant = retrieve(applicant.getApplicantId());
			logger.info("updated Applicant : " + updatedApplicant);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return updatedApplicant;
	}

	@Override
	/**
	 * Retrieves all  applicants 
	 * 
	 */
	public List<Applicant> retrieveAll() {

		List<Applicant> applicants = new ArrayList<Applicant>();
		try {
			Applicant applicant = null;
			MessageFormat messageFormat = new MessageFormat(selectAllApplicantQuery);
			selectAllApplicantQuery = messageFormat.format(new Object[] { TBL_APPLICANT });

			logger.info("Select all applicant query : " + selectAllApplicantQuery);

			statement = DBConnection.getStatement(selectAllApplicantQuery);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {

				applicant = new Applicant();
				applicant.setApplicantId(resultSet.getInt(1));
				applicant.setName(resultSet.getString(2));
				applicant.setExperience(resultSet.getDouble(3));
				applicant.setHighest_qual(resultSet.getString(4));
				applicant.setEmail(resultSet.getString(5));
				applicant.setContact(resultSet.getString(6));
				applicants.add(applicant);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug("List of applicants : " + applicants);
		return applicants;
	}

	@Override
	/**
	 * Retrieves applicant by applicantId
	 */
	public Applicant retrieve(int applicantId) {
		Applicant applicant = new Applicant();
		try {
			MessageFormat messageFormat = new MessageFormat(selectApplicantQuery);
			selectApplicantQuery = messageFormat.format(new Object[] { TBL_APPLICANT, COL_APPLICANT_ID });
			statement = DBConnection.getStatement(selectApplicantQuery);
			statement.setInt(1, applicantId);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				applicant.setApplicantId(resultSet.getInt(1));
				applicant.setName(resultSet.getString(2));
				applicant.setExperience(resultSet.getDouble(3));
				applicant.setHighest_qual(resultSet.getString(4));
				applicant.setEmail(resultSet.getString(5));
				applicant.setContact(resultSet.getString(6));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return applicant;
	}
}
