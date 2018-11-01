/**
 * 
 */
package com.axonactive.common.util.dataset;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.naming.NamingException;

import org.dbunit.AbstractDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSetWriter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;

/**
 * @author nvmuon
 *
 */
public abstract class AbstractDataSetManager implements DataSetManager {

	private AbstractDatabaseTester databaseTester;

	/**
	 * @param jndiName
	 * @throws NamingException 
	 */
	public AbstractDataSetManager(AbstractDatabaseTester databaseTester)  {
		this.databaseTester = databaseTester;
	}

	@Override
	public void oneSetup(String dataSetUrl) throws Exception {
			//Read test data to dataset object
			InputStream is = new FileInputStream(dataSetUrl);
			FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
			IDataSet dataSet = builder.build(is);
			//Clean up and initialize test data
			databaseTester.setDataSet(dataSet);
			databaseTester.onSetup();
	}

	@Override
	public void onTearDown() throws Exception {
			this.databaseTester.onTearDown();
	}

	@Override
	public void exportDataAsXml(String targetFile) throws Exception {
			IDataSet expoertedDataSet = this.databaseTester.getConnection().createDataSet();
			FlatXmlDataSet.write(expoertedDataSet, new FileOutputStream(targetFile));
	}

	@Override
	public void exportDataAsCsv(String targetFile) throws Exception {
			IDataSet expoertedDataSet = this.databaseTester.getConnection().createDataSet();
			CsvDataSetWriter.write(expoertedDataSet, new File(targetFile));
	}

	@Override
	public void SetSetUpOperation(DatabaseOperation setUpOperation) {
		this.databaseTester.setSetUpOperation(setUpOperation);
	}

	@Override
	public void setTearDownOperation(DatabaseOperation tearDownOperation) {
		this.databaseTester.setTearDownOperation(tearDownOperation);
	}
}
