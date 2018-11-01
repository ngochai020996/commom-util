/**
 * 
 */
package com.axonactive.common.util.dataset;

import org.dbunit.JdbcDatabaseTester;

/**
 * @author nvmuon
 *
 */
public class PropertiesJdbcDataSetManager extends JdbcDataSetManager {

	/** A key for property that defines the connection url */
	public static final String DB_CONNECTION_URL = "db.connectionUrl";
	/** A key for property that defines the driver classname */
	public static final String DB_DRIVER_CLASS = "db.driverClass";
	/** A key for property that defines the user's password */
	public static final String DB_PASSWORD = "db.password";
	/** A key for property that defines the username */
	public static final String DB_USERNAME = "db.username";
	/** A key for property that defines the database schema */
	public static final String DB_SCHEMA = "db.schema";

	
	/**
	 * Creates a new {@link JdbcDatabaseTester} using specific {@link System#getProperty(String)}
	 * values as initialization parameters
	 * @throws ClassNotFoundException 
	 * @throws Exception
	 */
	public PropertiesJdbcDataSetManager() throws ClassNotFoundException {
		super(System.getProperty(DB_DRIVER_CLASS), 
				System.getProperty(DB_CONNECTION_URL), 
				System.getProperty(DB_USERNAME), 
				System.getProperty(DB_PASSWORD),
				System.getProperty(DB_SCHEMA));
	}

}
