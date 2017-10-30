package com.recruitment.dao;

import static com.recruitment.dao.util.DBConstants.COL_CONTACT;
import static com.recruitment.dao.util.DBConstants.COL_DEPARTMENT;
import static com.recruitment.dao.util.DBConstants.COL_HR_ID;
import static com.recruitment.dao.util.DBConstants.COL_HR_NAME;
import static com.recruitment.dao.util.DBConstants.COL_PASSWORD;
import static com.recruitment.dao.util.DBConstants.TBL_HR;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.db.DBConnection;
import com.recruitment.model.HumanResource;

/**
 * {@code HumanResourceDAO} provides various functionalities to interact with
 * database. It provides add,update,read and delete operations for
 * <i>HumanResource</i> entity bean
 * 
 * @author Dhanraj Popalghat
 * @author Akash Mithari
 * @author Harshada Harde
 *
 */
public class HumanResourceDAO implements GenericDAOContract<HumanResource> {

	// All sql queries for HumanResource entity
	private String insertHRQuery = "INSERT INTO {0} ({1},{2},{3},{4}) VALUES (?,?,?,?)";
	private String selectHRQuery = "SELECT * FROM {0} WHERE {1}=?";
	private String selectAllHRs = "SELECT * FROM {0}";
	private String deleteHr = "DELETE FROM {0} WHERE {1}=?";
	private String updateHR = "UPDATE {0} SET {1}=?,{2}=?,{3}=?,{4}=? WHERE {5}=?";

	private static Logger logger = Logger.getLogger(PositionDAO.class);
	private PreparedStatement statement;
	private ResultSet resultSet;

	/**
	 * Inserts <i>HumanResource</i> into database
	 * 
	 * @param humanResource
	 *            instance of HumanResource
	 * @return true in case of insertion else false
	 */
	@Override
	public HumanResource add(HumanResource humanResource) {
		try {
			MessageFormat messageFormat = new MessageFormat(insertHRQuery);
			insertHRQuery = messageFormat
					.format(new Object[] { TBL_HR, COL_HR_NAME, COL_DEPARTMENT, COL_CONTACT, COL_PASSWORD });

			statement = DBConnection.getStatement(insertHRQuery);

			statement.setString(1, humanResource.getName());
			statement.setString(2, humanResource.getDepartment());
			statement.setString(3, humanResource.getContact());
			statement.setString(4, humanResource.getPassword());

			int check = statement.executeUpdate();

			logger.debug("HR inserted : " + check);
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				System.out.println(generatedKeys.getInt(1));
				humanResource.setHrId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return humanResource;
	}

	/**
	 * Deletes <i>HumanResource</i> instance from database
	 * 
	 * @param hrID
	 *            unique identifier of HumanResource
	 * @return true in case of deletion else false
	 */
	@Override
	public boolean delete(int hrID) {
		try {
			MessageFormat messageFormat = new MessageFormat(deleteHr);
			deleteHr = messageFormat.format(new Object[] { TBL_HR, COL_HR_ID });
			statement = DBConnection.getStatement(deleteHr);
			statement.setInt(1, hrID);
			int check = statement.executeUpdate();
			logger.info("HR deleted with id : " + hrID);
			if (check > 0)
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	/**
	 * 
	 * Updates <i>HumanResource</i> instance in the database
	 * 
	 * @param humanResource
	 *            instance of HumanResource
	 * @return instance of HumanResource
	 * 
	 */
	@Override
	public HumanResource update(HumanResource humanResource) {
		HumanResource updatedHumanResource = null;
		try {
			MessageFormat messageFormat = new MessageFormat(updateHR);
			updateHR = messageFormat
					.format(new Object[] { TBL_HR, COL_HR_NAME, COL_DEPARTMENT, COL_CONTACT, COL_PASSWORD, COL_HR_ID });

			// Position object from database
			HumanResource hrInDB = new HumanResourceDAO().retrieve(humanResource.getHrId());
			statement = DBConnection.getStatement(updateHR);

			if (humanResource.getName() == null)
				humanResource.setName(hrInDB.getName());

			if (humanResource.getDepartment() == null)
				humanResource.setDepartment(hrInDB.getDepartment());

			if (humanResource.getContact() == null)
				humanResource.setContact(hrInDB.getContact());

			if (humanResource.getPassword() == null) {
				humanResource.setPassword(hrInDB.getPassword());
			}

			statement.setString(1, humanResource.getName());
			statement.setString(2, humanResource.getDepartment());
			statement.setString(3, humanResource.getContact());
			statement.setString(4, humanResource.getPassword());
			statement.setInt(5, humanResource.getHrId());

			int check = statement.executeUpdate();
			if (check > 0)
				logger.debug("HR updated with id : " + humanResource.getHrId());
			else
				logger.debug("failed to update HR");
			updatedHumanResource = retrieve(humanResource.getHrId());
			logger.info("updated HR : " + updatedHumanResource);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return updatedHumanResource;
	}

	/**
	 * Retrieves all HRs in the database
	 * 
	 * @return all HR entities in the database
	 */
	@Override
	public List<HumanResource> retrieveAll() {
		List<HumanResource> humanResources = new ArrayList<HumanResource>();
		HumanResource humanResource = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectAllHRs);
			selectAllHRs = messageFormat.format(new Object[] { TBL_HR });

			statement = DBConnection.getStatement(selectAllHRs);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				humanResource = new HumanResource();
				humanResource.setHrId(resultSet.getInt(1));
				humanResource.setName(resultSet.getString(2));
				humanResource.setDepartment(resultSet.getString(3));
				humanResource.setContact(resultSet.getString(4));
				humanResource.setPassword(resultSet.getString(5));
				humanResources.add(humanResource);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug(humanResources);
		return humanResources;
	}

	/**
	 * Retrieves an instance of <i>HumanResource</i> entity bean for specific id
	 * 
	 * @param hrID
	 *            a unique identifier
	 * @return instance of HumanResource
	 */
	@Override
	public HumanResource retrieve(int hrID) {
		HumanResource humanResource = new HumanResource();
		try {
			MessageFormat messageFormat = new MessageFormat(selectHRQuery);
			selectHRQuery = messageFormat.format(new Object[] { TBL_HR, COL_HR_ID });

			statement = DBConnection.getStatement(selectHRQuery);
			statement.setInt(1, hrID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				humanResource.setHrId(resultSet.getInt(1));
				humanResource.setName(resultSet.getString(2));
				humanResource.setDepartment(resultSet.getString(3));
				humanResource.setContact(resultSet.getString(4));
				humanResource.setPassword(resultSet.getString(5));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug(humanResource);
		return humanResource;
	}
}
