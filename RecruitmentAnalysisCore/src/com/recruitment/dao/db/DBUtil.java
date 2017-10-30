package com.recruitment.dao.db;

import static com.recruitment.dao.util.DBConstants.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	private static String database = "recruitment";
	private static String APPLICANT_ID_QUERY = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE "
			+ "TABLE_SCHEMA =\"" + database + "\" AND TABLE_NAME = \"" + TBL_APPLICANT + "\"";

	private static String HR_ID_QUERY = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE "
			+ "TABLE_SCHEMA =\"" + database + "\" AND TABLE_NAME = \"" + TBL_HR + "\"";

	private static String POST_ID_QUERY = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE "
			+ "TABLE_SCHEMA =\"" + database + "\" AND TABLE_NAME = \"" + TBL_POST + "\"";

	private static String SKILLSET_ID_QUERY = "SELECT AUTO_INCREMENT FROM information_schema.TABLES WHERE "
			+ "TABLE_SCHEMA =\"" + database + "\" AND TABLE_NAME = \"" + TBL_SKILLSET + "\"";

	private static PreparedStatement statement;
	private static ResultSet resultSet;

	public static int getLatestIDForApplicant() {
		int applicantId = 0;
		try {
			statement = DBConnection.getStatement(APPLICANT_ID_QUERY);
			System.out.println(APPLICANT_ID_QUERY);
			resultSet = statement.executeQuery();
			if (resultSet.next())
				applicantId = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return applicantId-1;
	}

	public static int getLatestIDForHR() {
		int hrId = 0;
		try {
			statement = DBConnection.getStatement(HR_ID_QUERY);
			System.out.println(HR_ID_QUERY);
			resultSet = statement.executeQuery();
			if (resultSet.next())
				hrId = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hrId-1;
	}

	public static int getLatestIDForPost() {
		int postId = 0;
		try {
			statement = DBConnection.getStatement(POST_ID_QUERY);
			System.out.println(POST_ID_QUERY);
			resultSet = statement.executeQuery();
			if (resultSet.next())
				postId = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return postId-1;
	}

	public static int getLatestIDForSkillSet() {
		int skillSetId = 0;
		try {
			statement = DBConnection.getStatement(SKILLSET_ID_QUERY);
			System.out.println(SKILLSET_ID_QUERY);
			resultSet = statement.executeQuery();
			if (resultSet.next())
				skillSetId = resultSet.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return skillSetId-1;
	}
}
