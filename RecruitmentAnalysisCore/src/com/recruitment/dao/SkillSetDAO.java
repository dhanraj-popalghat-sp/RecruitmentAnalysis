package com.recruitment.dao;

import static com.recruitment.dao.util.DBConstants.COL_SKILL_ID;
import static com.recruitment.dao.util.DBConstants.COL_SKILL_NAME;
import static com.recruitment.dao.util.DBConstants.TBL_SKILLSET;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.recruitment.dao.db.DBConnection;
import com.recruitment.model.SkillSet;

/**
 * 
 * {@code SkillSetDAo} provides multiple functionalities to interact with
 * database. It provides methods for adding,deleting,updating and searching
 * {@link Skill}
 * 
 * @author Dhanraj Popalghat
 * @author Akash Mithari
 * @author Harshada Harde
 *
 */
public class SkillSetDAO implements GenericDAOContract<SkillSet> {

	private String selectSkillSetQuery = "SELECT * FROM {0} WHERE {1}=?";
	private String selectAllSkillSetQuery = "SELECT * FROM {0}";
	private String insertSkillSet = "INSERT INTO {0} ({1}) VALUES (?)";
	private String updateSkillSet = "UPDATE {0} SET {1}=? WHERE {2}=?";
	private String deleteSkillSet = "DELETE FROM {0} WHERE {1}=?";

	private static Logger logger = Logger.getLogger(PositionDAO.class);
	private PreparedStatement statement;
	private ResultSet resultSet;

	@Override
	public SkillSet add(SkillSet skillSet) {
		try {
			MessageFormat messageFormat = new MessageFormat(insertSkillSet);
			insertSkillSet = messageFormat.format(new Object[] { TBL_SKILLSET, COL_SKILL_NAME });

			statement = DBConnection.getStatement(insertSkillSet);

			statement.setString(1, skillSet.getSkillName());

			int check = statement.executeUpdate();

			logger.debug("Skillset inserted : " + check);
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				System.out.println(generatedKeys.getInt(1));
				skillSet.setSkillId(generatedKeys.getInt(1));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return skillSet;
	}

	@Override
	public boolean delete(int skillSetID) {
		try {
			MessageFormat messageFormat = new MessageFormat(deleteSkillSet);
			deleteSkillSet = messageFormat.format(new Object[] { TBL_SKILLSET, COL_SKILL_ID });
			statement = DBConnection.getStatement(deleteSkillSet);
			statement.setInt(1, skillSetID);
			int check = statement.executeUpdate();
			logger.info("skillSet deleted with id : " + skillSetID);
			if (check > 0)
				return true;

		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public SkillSet update(SkillSet skillSet) {
		SkillSet updatedSkillSet = null;
		try {
			MessageFormat messageFormat = new MessageFormat(updateSkillSet);
			updateSkillSet = messageFormat.format(new Object[] { TBL_SKILLSET, COL_SKILL_NAME, COL_SKILL_ID });

			statement = DBConnection.getStatement(updateSkillSet);

			statement.setString(1, skillSet.getSkillName());
			statement.setInt(2, skillSet.getSkillId());

			int check = statement.executeUpdate();
			if (check > 0)
				logger.debug("skillset updated with id : " + skillSet.getSkillId());
			else
				logger.debug("failed to update skillset");
			updatedSkillSet = retrieve(skillSet.getSkillId());
			logger.info("updated skillset : " + updatedSkillSet);
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		return updatedSkillSet;
	}

	@Override
	public List<SkillSet> retrieveAll() {
		List<SkillSet> skillSets = new ArrayList<SkillSet>();
		SkillSet skillSet = null;
		try {
			MessageFormat messageFormat = new MessageFormat(selectAllSkillSetQuery);
			selectAllSkillSetQuery = messageFormat.format(new Object[] { TBL_SKILLSET });

			statement = DBConnection.getStatement(selectAllSkillSetQuery);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				skillSet = new SkillSet();
				skillSet.setSkillId(resultSet.getInt(1));
				skillSet.setSkillName(resultSet.getString(2));
				skillSets.add(skillSet);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug(skillSets);
		return skillSets;
	}

	@Override
	public SkillSet retrieve(int skillID) {
		SkillSet skillSet = new SkillSet();
		try {
			MessageFormat messageFormat = new MessageFormat(selectSkillSetQuery);
			selectSkillSetQuery = messageFormat.format(new Object[] { TBL_SKILLSET, COL_SKILL_ID });

			statement = DBConnection.getStatement(selectSkillSetQuery);
			statement.setInt(1, skillID);
			resultSet = statement.executeQuery();

			while (resultSet.next()) {
				skillSet.setSkillId(resultSet.getInt(1));
				skillSet.setSkillName(resultSet.getString(2));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		}
		logger.debug(skillSet);
		return skillSet;
	}

}
