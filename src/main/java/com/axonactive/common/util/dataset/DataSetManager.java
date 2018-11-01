/**
 * 
 */
package com.axonactive.common.util.dataset;

import org.dbunit.operation.DatabaseOperation;

/**
 * @author nvmuon
 *
 */
public interface DataSetManager {

	public void oneSetup(String dataSetUrl) throws Exception;

	public void onTearDown() throws Exception;
	
	public void exportDataAsXml(String targetFile) throws Exception;
	
	public void exportDataAsCsv(String targetFile) throws Exception;
	
	public void SetSetUpOperation(DatabaseOperation setUpOperation);
	
	public void setTearDownOperation(DatabaseOperation tearDownOperation);
	
}
