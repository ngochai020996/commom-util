/**
 * 
 */
package com.axonactive.common.util.dataset;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.dbunit.DataSourceDatabaseTester;

/**
 * @author nvmuon
 *
 */
public class DataSourceDataSetManager extends AbstractDataSetManager {

	/**
	 * @param jndiName
	 * @throws NamingException 
	 */
	public DataSourceDataSetManager(String jndiName) throws NamingException  {
		super(new DataSourceDatabaseTester(DataSourceDataSetManager.lookupDataSource(jndiName)));
	}

	
	public static DataSource lookupDataSource(String jndiName) throws NamingException {
		Context context =  new InitialContext();
		return (DataSource)context.lookup(jndiName);
	}

	

}
 