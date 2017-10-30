package com.recruitment.dao;

import static com.recruitment.dao.util.DBConstants.COL_APPLICANT_ID;
import static com.recruitment.dao.util.DBConstants.COL_DATE_OF_APPLY;
import static com.recruitment.dao.util.DBConstants.COL_POST_ID;
import static com.recruitment.dao.util.DBConstants.TBL_APPLICANT_APPLY_POST;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.db.DBConnection;
import com.recruitment.model.Applicant;
import com.recruitment.model.Apply;
import com.recruitment.model.Position;

/**
 * 
 * {@code ApplyDAO} provides multiple functionalities to interact with database.
 * It provides methods for adding,deleting,updating and searching {@link Apply}
 * 
 * @author Dhanraj Popalghat
 * @author Akash Mithari
 * @author Harshada Harde
 *
 */
public class ApplyDAO implements GenericDAOContract<Apply> {

	private String selectApplyQuery = "SELECT * FROM {0} WHERE {1}=? AND {2}=?";
	private String selectAllApplyQuery = "SELECT * FROM {0}";
	private String insertApply = "INSERT INTO {0} VALUES (?,?,?)";
	private String deleteApply = "DELETE FROM {0} WHERE {1}=? AND {2}=?";

	private static Logger logger = Logger.getLogger(PositionDAO.class);
	private PreparedStatement statement;
	private ResultSet resultSet;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.dao.GenericDAOContract#add(java.lang.Object)
	 */
	public boolean addApply(Apply apply) {
		try {
			MessageFormat messageFormat = new MessageFormat(insertApply);
			insertApply = messageFormat.format(
					new Object[] { TBL_APPLICANT_APPLY_POST, COL_POST_ID, COL_APPLICANT_ID, COL_DATE_OF_APPLY });

			statement = DBConnection.getStatement(insertApply);

			statement.setInt(1, apply.getPost().getPostId());
			statement.setInt(2, apply.getApplicant().getApplicantId());
			statement.setDate(3, apply.getDateOfApply());

			int check = statement.executeUpdate();

			logger.debug("applicant applied : " + check);
			if (check > 0)
				return true;
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
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

	public boolean delete(int applicantId, int postID) {
		try {
			MessageFormat messageFormat = new MessageFormat(deleteApply);
			deleteApply = messageFormat
					.format(new Object[] { TBL_APPLICANT_APPLY_POST, COL_POST_ID, COL_APPLICANT_ID });
			statement = DBConnection.getStatement(deleteApply);
			statement.setInt(1, postID);
			statement.setInt(2, applicantId);

			int check = statement.executeUpdate();
			logger.info("application for the post " + postID + " is deleted");
			if (check > 0)
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.dao.GenericDAOContract#update(java.lang.Object)
	 */
	@Override
	public Apply update(Apply resource) {
		throw new UnsupportedOperationException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.dao.GenericDAOContract#retrieveAll()
	 */
	@Override
	public List<Apply> retrieveAll() {
		List<Apply> listOfApply = new ArrayList<Apply>();
		Apply apply = null;
		Position position = null;
		Applicant applicant = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectAllApplyQuery);
			selectAllApplyQuery = messageFormat.format(new Object[] { TBL_APPLICANT_APPLY_POST });

			statement = DBConnection.getStatement(selectAllApplyQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				position = new Position();
				applicant = new Applicant();
				apply = new Apply();
				position.setPostId(resultSet.getInt(1));
				apply.setPost(position);
				applicant.setApplicantId(resultSet.getInt(2));
				apply.setApplicant(applicant);
				apply.setDateOfApply(resultSet.getDate(3));

				listOfApply.add(apply);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug(listOfApply);
		return listOfApply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.dao.GenericDAOContract#retrieve(int)
	 */
	public Apply retrieve(int applicantID, int postID) {
		Apply apply = new Apply();
		Position position = new Position();
		Applicant applicant = new Applicant();
		try {
			MessageFormat messageFormat = new MessageFormat(selectApplyQuery);
			selectApplyQuery = messageFormat
					.format(new Object[] { TBL_APPLICANT_APPLY_POST, COL_POST_ID, COL_APPLICANT_ID });

			statement = DBConnection.getStatement(selectApplyQuery);
			statement.setInt(1, postID);
			statement.setInt(2, applicantID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				position.setPostId(resultSet.getInt(1));
				apply.setPost(position);
				applicant.setApplicantId(resultSet.getInt(2));
				apply.setApplicant(applicant);
				apply.setDateOfApply(resultSet.getDate(3));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug(apply);
		return apply;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.recruitment.dao.GenericDAOContract#retrieve(int)
	 */
	@Override
	public Apply retrieve(int id) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Apply add(Apply resource) {
		throw new UnsupportedOperationException();
	}

}
