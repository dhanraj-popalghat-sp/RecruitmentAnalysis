package com.recruitment.dao;

import static com.recruitment.dao.util.DBConstants.COL_APPLICANT_ID;
import static com.recruitment.dao.util.DBConstants.COL_APPLICANT_RESULT;
import static com.recruitment.dao.util.DBConstants.COL_DATE_OF_INTERVIEW;
import static com.recruitment.dao.util.DBConstants.COL_DATE_OF_JOINING;
import static com.recruitment.dao.util.DBConstants.COL_JOINING_STATUS;
import static com.recruitment.dao.util.DBConstants.COL_LOCATION;
import static com.recruitment.dao.util.DBConstants.COL_POST_ID;
import static com.recruitment.dao.util.DBConstants.TBL_INTERVIEW;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.db.DBConnection;
import com.recruitment.model.Applicant;
import com.recruitment.model.Interview;
import com.recruitment.model.Position;

/**
 * 
 * {@code ApplyDAO} provides multiple functionalities to interact with
 * database. It provides methods for adding,deleting,updating and searching
 * {@link Apply}
 * 
 * @author Dhanraj Popalghat
 * @author Akash Mithari
 * @author Harshada Harde
 *
 */
public class InterviewDAO implements GenericDAOContract<Interview> {

	// All interview table related sql queries
	private String insertQuery = "INSERT INTO {0} ({1},{2},{3},{4},{5},{6},{7}) values (?,?,?,?,?,?,?)";
	private String selectInterviewQuery = "SELECT * FROM {0} WHERE {1}=? AND {2}=?";
	private String selectAllInterview = "SELECT * FROM {0}";
	private String updateInterviewQuery = "UPDATE {0} SET {1}=?,{2}=?,{3}=?,{4}=?,{5}=? WHERE {6}=? AND {7}=?";
	private String deleteInterviewQuery = "DELETE FROM {0} WHERE {1}=? AND {2}=?";

	private static Logger logger = Logger.getLogger(PositionDAO.class);
	private PreparedStatement stmt;
	private ResultSet resultSet;

	public boolean addInterview(Interview interview) {
		try {
			MessageFormat messageFormat = new MessageFormat(insertQuery);
			insertQuery = messageFormat
					.format(new Object[] { TBL_INTERVIEW, COL_APPLICANT_ID, COL_POST_ID, COL_DATE_OF_INTERVIEW,
							COL_DATE_OF_JOINING, COL_LOCATION, COL_APPLICANT_RESULT, COL_JOINING_STATUS });

			logger.info("interview insert query : " + insertQuery);

			stmt = DBConnection.getStatement(insertQuery);
			stmt.setInt(1, interview.getApplicant().getApplicantId());
			stmt.setInt(2, interview.getPosition().getPostId());
			stmt.setDate(3, interview.getDateOfInterview());
			stmt.setDate(4, interview.getDateOfJoining());
			stmt.setString(5, interview.getLocation());
			stmt.setBoolean(6, interview.isApplicantResult());
			stmt.setBoolean(7, interview.isJoiningStatus());

			int check = stmt.executeUpdate();
			logger.debug("interview added check : " + check);
			if (check > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean delete(int applicantId, int positionId) {
		try {
			MessageFormat messageFormat = new MessageFormat(deleteInterviewQuery);
			deleteInterviewQuery = messageFormat.format(new Object[] { TBL_INTERVIEW, COL_APPLICANT_ID, COL_POST_ID });

			stmt = DBConnection.getStatement(deleteInterviewQuery);
			stmt.setInt(1, applicantId);
			stmt.setInt(2, positionId);

			int check = stmt.executeUpdate();
			logger.info("interview deleted : " + check);
			if (check > 0)
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public Interview update(Interview interview) {
		Interview updatedInterview = null;
		try {
			MessageFormat messageFormat = new MessageFormat(updateInterviewQuery);
			updateInterviewQuery = messageFormat
					.format(new Object[] { TBL_INTERVIEW, COL_DATE_OF_INTERVIEW, COL_DATE_OF_JOINING, COL_LOCATION,
							COL_APPLICANT_RESULT, COL_JOINING_STATUS, COL_APPLICANT_ID, COL_POST_ID });

			logger.debug("update query : " + updateInterviewQuery);
			// Position object from database
			Interview interviewInDB = new InterviewDAO().getInterviewById(interview.getApplicant().getApplicantId(),
					interview.getPosition().getPostId());
			stmt = DBConnection.getStatement(updateInterviewQuery);

			if (interview.getDateOfInterview() == null)
				interview.setDateOfInterview(interviewInDB.getDateOfInterview());

			if (interview.getDateOfJoining() == null)
				interview.setDateOfJoining(interviewInDB.getDateOfJoining());

			if (interview.getLocation() == null)
				interview.setLocation(interviewInDB.getLocation());

			stmt.setDate(1, interview.getDateOfInterview());
			stmt.setDate(2, interview.getDateOfJoining());
			stmt.setString(3, interview.getLocation());
			stmt.setBoolean(4, interview.isApplicantResult());
			stmt.setBoolean(5, interview.isJoiningStatus());
			stmt.setInt(6, interview.getApplicant().getApplicantId());
			stmt.setInt(7, interview.getPosition().getPostId());

			int check = stmt.executeUpdate();
			if (check > 0)
				logger.debug("interview updated with post id : " + interview.getPosition().getPostId() + " and "
						+ interview.getApplicant().getApplicantId());
			else
				logger.debug("failed to update interview");
			updatedInterview = getInterviewById(interview.getApplicant().getApplicantId(),
					interview.getPosition().getPostId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return updatedInterview;
	}

	@Override
	public List<Interview> retrieveAll() {

		List<Interview> interviews = new ArrayList<Interview>();
		Interview interview = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectAllInterview);
			selectAllInterview = messageFormat.format(new Object[] { TBL_INTERVIEW });
			stmt = DBConnection.getStatement(selectAllInterview);
			resultSet = stmt.executeQuery();

			Position post = new Position();
			Applicant applicant = new Applicant();

			while (resultSet.next()) {

				interview = new Interview();

				applicant.setApplicantId(resultSet.getInt(1));
				interview.setApplicant(applicant);
				post.setPostId(resultSet.getInt(2));
				interview.setPosition(post);
				interview.setDateOfInterview(resultSet.getDate(3));
				interview.setDateOfJoining(resultSet.getDate(4));
				interview.setLocation(resultSet.getString(5));
				interview.setApplicantResult(resultSet.getBoolean(6));
				interview.setJoiningStatus(resultSet.getBoolean(7));

				interviews.add(interview);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("All interviews : " + interviews);
		return interviews;
	}

	@Override
	public Interview retrieve(int id) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Retrieves interview entity by its applicationId and postId
	 * 
	 * @param applicantId
	 *            applied candidate id
	 * @param postId
	 *            post id to which candidate applied to
	 * @return interview bean object
	 */
	public Interview getInterviewById(int applicantId, int postId) {

		Interview interview = new Interview();
		Position position = new Position();
		Applicant applicant = new Applicant();

		try {
			MessageFormat messageFormat = new MessageFormat(selectInterviewQuery);
			selectInterviewQuery = messageFormat.format(new Object[] { TBL_INTERVIEW, COL_APPLICANT_ID, COL_POST_ID });

			stmt = DBConnection.getStatement(selectInterviewQuery);
			stmt.setInt(1, applicantId);
			stmt.setInt(2, postId);

			resultSet = stmt.executeQuery();

			while (resultSet.next()) {

				applicant.setApplicantId(resultSet.getInt(1));
				interview.setApplicant(applicant);
				position.setPostId(resultSet.getInt(2));
				interview.setPosition(position);
				interview.setDateOfInterview(resultSet.getDate(3));
				interview.setDateOfJoining(resultSet.getDate(4));
				interview.setLocation(resultSet.getString(5));
				interview.setApplicantResult(resultSet.getBoolean(6));
				interview.setJoiningStatus(resultSet.getBoolean(7));
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		logger.debug("Get interview object : " + interview);
		return interview;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.dao.GenericDAOContract#delete(int)
	 */
	@Override
	public boolean delete(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Interview add(Interview resource) {
		throw new UnsupportedOperationException();
	}
}
