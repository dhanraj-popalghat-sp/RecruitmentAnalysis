package com.recruitment.dao;

import static com.recruitment.dao.util.DBConstants.COL_DATE_OF_POST;
import static com.recruitment.dao.util.DBConstants.COL_EXPERIENCE;
import static com.recruitment.dao.util.DBConstants.COL_HR_ID;
import static com.recruitment.dao.util.DBConstants.COL_NO_OF_POSTS;
import static com.recruitment.dao.util.DBConstants.COL_POST_ID;
import static com.recruitment.dao.util.DBConstants.COL_POST_NAME;
import static com.recruitment.dao.util.DBConstants.COL_SKILL_ID;
import static com.recruitment.dao.util.DBConstants.COL_STATUS;
import static com.recruitment.dao.util.DBConstants.TBL_POST;
import static com.recruitment.dao.util.DBConstants.TBL_POST_SKILLSET;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.db.DBConnection;
import com.recruitment.model.HumanResource;
import com.recruitment.model.Position;

/**
 * 
 * {@code PositionDAO} provides multiple functionalities to interact with
 * database. It provides methods for adding,deleting,updating and searching
 * {@link Position}
 * 
 * @author Dhanraj Popalghat
 * @author Akash Mithari
 * @author Harshada Harde
 *
 */
public class PositionDAO implements GenericDAOContract<Position> {

	// All database queries for position entity
	private String insertQuery = "INSERT INTO {0} ({1},{2},{3},{4},{5},{6}) values (?,?,?,?,?,?)";
	private String selectPositionByPositionName = "SELECT * FROM {0} WHERE {1}=?";
	private String selectPositionsBySkillSet = "SELECT {0}.* FROM {0} INNER JOIN {1} ON {0}.{2}={1}.{2} WHERE {1}.{3}=?";
	private String selectPositionByMaxExperience = "SELECT * FROM {0} WHERE {1}>?";
	private String selectPositionByMinExperience = "SELECT * FROM {0} WHERE {1}<?";
	private String selectPositionByID = "SELECT * FROM {0} WHERE {1}=?";
	private String selectAllPositions = "SELECT * FROM {0}";
	private String deleteQuery = "DELETE FROM {0} WHERE {1}=?";
	private String updateQuery = "UPDATE {0} SET {1}=?,{2}=?,{3}=?,{4}=?,{5}=?,{6}=? WHERE {7}=?";

	private static Logger logger = Logger.getLogger(PositionDAO.class);
	private PreparedStatement stmt;
	private ResultSet resultSet;

	@Override
	public Position add(Position position) {

		try {
			MessageFormat messageFormat = new MessageFormat(insertQuery);
			insertQuery = messageFormat.format(new Object[] { TBL_POST, COL_POST_NAME, COL_NO_OF_POSTS, COL_EXPERIENCE,
					COL_HR_ID, COL_DATE_OF_POST, COL_STATUS });

			stmt = DBConnection.getStatement(insertQuery);
			stmt.setString(1, position.getPostName());
			stmt.setInt(2, position.getNoOfPosts());
			stmt.setDouble(3, position.getExperience());
			stmt.setInt(4, position.getHr().getHrId());
			stmt.setDate(5, position.getDateOfPost());
			stmt.setBoolean(6, position.isStatus());

			int check = stmt.executeUpdate();
			ResultSet generatedKeys = stmt.getGeneratedKeys();
			if (generatedKeys.next()) {
				System.out.println(generatedKeys.getInt(1));
				position.setPostId(generatedKeys.getInt(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return position;
	}

	@Override
	public boolean delete(int positionId) {
		try {
			MessageFormat messageFormat = new MessageFormat(deleteQuery);
			deleteQuery = messageFormat.format(new Object[] { TBL_POST, COL_POST_ID });

			stmt = DBConnection.getStatement(deleteQuery);
			stmt.setInt(1, positionId);

			int check = stmt.executeUpdate();
			logger.info("Position deleted : " + check);
			if (check > 0)
				return true;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Position update(Position position) {

		Position updatedPost = null;
		try {
			MessageFormat messageFormat = new MessageFormat(updateQuery);
			updateQuery = messageFormat.format(new Object[] { TBL_POST, COL_POST_NAME, COL_NO_OF_POSTS, COL_EXPERIENCE,
					COL_HR_ID, COL_DATE_OF_POST, COL_STATUS, COL_POST_ID });
			HumanResource hr = new HumanResource();

			// Position object from database
			Position positionInDB = new PositionDAO().retrieve(position.getPostId());
			stmt = DBConnection.getStatement(updateQuery);

			if (position.getPostName() == null)
				position.setPostName(positionInDB.getPostName());

			if (position.getNoOfPosts() == 0)
				position.setNoOfPosts(positionInDB.getNoOfPosts());

			if (position.getExperience() == 0)
				position.setExperience(positionInDB.getExperience());

			if (position.getHr() == null) {
				hr.setHrId(positionInDB.getHr().getHrId());
				position.setHr(hr);
			}

			if (position.getDateOfPost() == null)
				position.setDateOfPost(positionInDB.getDateOfPost());

			stmt.setString(1, position.getPostName());
			stmt.setInt(2, position.getNoOfPosts());
			stmt.setDouble(3, position.getExperience());
			stmt.setInt(4, position.getHr().getHrId());
			stmt.setDate(5, position.getDateOfPost());
			stmt.setBoolean(6, position.isStatus());
			stmt.setInt(7, position.getPostId());

			int check = stmt.executeUpdate();
			if (check > 0)
				logger.debug("position updated with position id : " + position.getPostId());
			else
				logger.debug("failed to update position");
			updatedPost = retrieve(position.getPostId());
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return updatedPost;
	}

	@Override
	public List<Position> retrieveAll() {

		List<Position> positions = new ArrayList<Position>();
		Position position = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectAllPositions);
			selectAllPositions = messageFormat.format(new Object[] { TBL_POST });
			stmt = DBConnection.getStatement(selectAllPositions);
			resultSet = stmt.executeQuery();

			HumanResource humanResource = new HumanResource();

			while (resultSet.next()) {

				position = new Position();
				position.setPostId(resultSet.getInt(1));
				position.setPostName(resultSet.getString(2));
				position.setNoOfPosts(resultSet.getInt(3));
				position.setExperience(resultSet.getDouble(4));
				humanResource.setHrId(resultSet.getInt(5));
				position.setHr(humanResource);
				position.setDateOfPost(resultSet.getDate(6));
				position.setStatus(resultSet.getBoolean(7));

				positions.add(position);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("All positions : " + positions);
		return positions;
	}

	@Override
	/**
	 * Retrieves position by positionId
	 * 
	 */
	public Position retrieve(int positionId) {
		Position position = new Position();
		try {
			MessageFormat messageFormat = new MessageFormat(selectPositionByID);
			selectPositionByID = messageFormat.format(new Object[] { TBL_POST, COL_POST_ID });
			stmt = DBConnection.getStatement(selectPositionByID);
			stmt.setInt(1, positionId);
			resultSet = stmt.executeQuery();

			HumanResource humanResource = new HumanResource();

			while (resultSet.next()) {
				position.setPostId(resultSet.getInt(1));
				position.setPostName(resultSet.getString(2));
				position.setNoOfPosts(resultSet.getInt(3));
				position.setExperience(resultSet.getDouble(4));
				humanResource.setHrId(resultSet.getInt(5));
				position.setHr(humanResource);
				position.setDateOfPost(resultSet.getDate(6));
				position.setStatus(resultSet.getBoolean(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return position;
	}

	/**
	 * 
	 * @param positionName
	 *            name of position
	 * @return position details
	 */
	public Position getPositionByPositionName(String positionName) {
		Position position = new Position();
		try {
			MessageFormat messageFormat = new MessageFormat(selectPositionByPositionName);
			selectPositionByPositionName = messageFormat.format(new Object[] { TBL_POST, COL_POST_NAME });
			stmt = DBConnection.getStatement(selectPositionByPositionName);
			stmt.setString(1, positionName);
			resultSet = stmt.executeQuery();

			HumanResource humanResource = new HumanResource();

			while (resultSet.next()) {
				position.setPostId(resultSet.getInt(1));
				position.setPostName(resultSet.getString(2));
				position.setNoOfPosts(resultSet.getInt(3));
				position.setExperience(resultSet.getDouble(4));
				humanResource.setHrId(resultSet.getInt(5));
				position.setHr(humanResource);
				position.setDateOfPost(resultSet.getDate(6));
				position.setStatus(resultSet.getBoolean(7));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return position;
	}

	/**
	 * 
	 * @param skillSetID
	 *            skillset unique identifier
	 * @return list of positions by skillset
	 */
	public List<Position> getPositionsBySkillSetID(int skillSetID) {
		List<Position> positions = new ArrayList<Position>();
		Position position = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectPositionsBySkillSet);

			selectPositionsBySkillSet = messageFormat
					.format(new Object[] { TBL_POST, TBL_POST_SKILLSET, COL_POST_ID, COL_SKILL_ID });

			stmt = DBConnection.getStatement(selectPositionsBySkillSet);
			stmt.setInt(1, skillSetID);
			resultSet = stmt.executeQuery();

			HumanResource humanResource = new HumanResource();

			while (resultSet.next()) {

				position = new Position();
				position.setPostId(resultSet.getInt(1));
				position.setPostName(resultSet.getString(2));
				position.setNoOfPosts(resultSet.getInt(3));
				position.setExperience(resultSet.getDouble(4));
				humanResource.setHrId(resultSet.getInt(5));
				position.setHr(humanResource);
				position.setDateOfPost(resultSet.getDate(6));
				position.setStatus(resultSet.getBoolean(7));
				positions.add(position);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return positions;
	}

	/**
	 * 
	 * @param experience
	 *            number of years
	 * @return list of positions
	 */
	public List<Position> getPositionsByMaxExperience(double experience) {
		List<Position> positions = new ArrayList<Position>();
		Position position = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectPositionByMaxExperience);

			selectPositionByMaxExperience = messageFormat.format(new Object[] { TBL_POST, COL_EXPERIENCE });
			logger.info(selectPositionByMaxExperience);
			stmt = DBConnection.getStatement(selectPositionByMaxExperience);
			stmt.setDouble(1, experience);
			resultSet = stmt.executeQuery();

			HumanResource humanResource = new HumanResource();

			while (resultSet.next()) {

				position = new Position();
				position.setPostId(resultSet.getInt(1));
				position.setPostName(resultSet.getString(2));
				position.setNoOfPosts(resultSet.getInt(3));
				position.setExperience(resultSet.getDouble(4));
				humanResource.setHrId(resultSet.getInt(5));
				position.setHr(humanResource);
				position.setDateOfPost(resultSet.getDate(6));
				position.setStatus(resultSet.getBoolean(7));
				positions.add(position);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return positions;
	}

	/**
	 * 
	 * @param experience
	 *            number of years
	 * @return list of positions
	 */
	public List<Position> getPositionsByMinExperience(double experience) {
		List<Position> positions = new ArrayList<Position>();
		Position position = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectPositionByMinExperience);

			selectPositionByMinExperience = messageFormat.format(new Object[] { TBL_POST, COL_EXPERIENCE });
			logger.info(selectPositionByMinExperience);
			stmt = DBConnection.getStatement(selectPositionByMinExperience);
			stmt.setDouble(1, experience);
			resultSet = stmt.executeQuery();

			HumanResource humanResource = new HumanResource();

			while (resultSet.next()) {

				position = new Position();
				position.setPostId(resultSet.getInt(1));
				position.setPostName(resultSet.getString(2));
				position.setNoOfPosts(resultSet.getInt(3));
				position.setExperience(resultSet.getDouble(4));
				humanResource.setHrId(resultSet.getInt(5));
				position.setHr(humanResource);
				position.setDateOfPost(resultSet.getDate(6));
				position.setStatus(resultSet.getBoolean(7));
				positions.add(position);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return positions;
	}
}
