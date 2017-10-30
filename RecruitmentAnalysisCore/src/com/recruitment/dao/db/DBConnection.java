package com.recruitment.dao.db;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * {@code DBConnection} provides methods for connecting particular database
 * 
 * @author Dhanraj Popalghat
 *
 */
public class DBConnection {

	private static Connection connection = null;
	public static Logger logger = Logger.getLogger(DBConnection.class);
	private static final String DB_PROPERTIES = "com/recruitment/resources/mysql_db.properties";

	private DBConnection() {
		super();
	}

	/**
	 * {@code getConnection()} provides {@link Connection} object for specified
	 * database
	 * 
	 * @return connection object
	 */
	public static Connection getConnection() {
		try {
			if (connection != null) {
				if (!connection.isClosed()) {
					return connection;
				}
			}
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			InputStream input = classLoader.getResourceAsStream(DB_PROPERTIES);
			Properties property = new Properties();
			logger.debug("file found : " + input);
			property.load(input);

			// Reading property file
			String dbDriver = property.getProperty("db.driver");
			String dbHost = property.getProperty("db.host");
			String dbProtocol = property.getProperty("db.protocol");
			String dbName = property.getProperty("db.dbname");
			String dbPort = property.getProperty("db.port");
			String dbUser = property.getProperty("db.user");
			String dbPassword = property.getProperty("db.password");

			// Connection String
			String connectionString = dbProtocol + "://" + dbHost + ":" + dbPort + "/" + dbName;

			logger.debug("Database Connection String : " + connectionString);

			// Loading drivers
			Class.forName(dbDriver);

			// Getting connection to database
			connection = DriverManager.getConnection(connectionString, dbUser, dbPassword);
		} catch (Exception e) {
			logger.error(e);
		}
		logger.info("Connection established " + connection);
		return connection;
	}

	/**
	 * {@code getStatement()} provides statement object for particular query
	 * 
	 * @param query
	 *            different database DDL,DML queries
	 * @return statement object for specific query
	 * 
	 * @throws SQLException
	 */
	public static PreparedStatement getStatement(String query) throws SQLException {
		logger.info("entered into getStatement()");
		connection = getConnection();
		PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		logger.debug("Statement is : " + statement);
		return statement;
	}
}
