/**
 * 
 */
package com.axonactive.common.util.dataset;

import org.dbunit.JdbcDatabaseTester;

/**
 * @author nvmuon
 *
 */
public class JdbcDataSetManager extends AbstractDataSetManager {
	
    
	/**
	 * @param databaseTester
	 * @param connectionUrl
	 * @param driverClass
	 * @param password
	 * @param username
     * @throws ClassNotFoundException 
	 */
	public JdbcDataSetManager(String driverClass, String connectionUrl, String username, String password) 
			throws ClassNotFoundException {
		
		super(new JdbcDatabaseTester(driverClass, connectionUrl, username, password));
		
	}
	
	/**
	 * @param databaseTester
	 * @param connectionUrl
	 * @param driverClass
	 * @param password
	 * @param username
	 * @param schema
     * @throws ClassNotFoundException 
	 */
	public JdbcDataSetManager(String driverClass, String connectionUrl, String username, String password, String schema) 
			throws ClassNotFoundException {
		
		super(new JdbcDatabaseTester(driverClass, connectionUrl, username, password, schema));
		
	}
    

}
